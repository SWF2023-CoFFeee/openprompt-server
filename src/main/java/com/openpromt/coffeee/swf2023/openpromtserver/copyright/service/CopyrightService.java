package com.openpromt.coffeee.swf2023.openpromtserver.copyright.service;


import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightRequest;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.dto.RegisterCopyrightResponse;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.entity.Copyright;
import com.openpromt.coffeee.swf2023.openpromtserver.copyright.repository.CopyrightRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.ipfs.service.FileService;
import com.openpromt.coffeee.swf2023.openpromtserver.ipfs.service.IpfsService;
import com.openpromt.coffeee.swf2023.openpromtserver.ownticket.dto.OwnTicketResponseDto;
import com.openpromt.coffeee.swf2023.openpromtserver.user.entity.User;
import com.openpromt.coffeee.swf2023.openpromtserver.user.repository.UserRepository;
import com.openpromt.coffeee.swf2023.openpromtserver.user.service.UserService;
import com.openpromt.coffeee.swf2023.openpromtserver.util.jaccard.Jaccard;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CopyrightService {
    private final FileService fileService;
    private final IpfsService ipfsService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CopyrightRepository copyrightRepository;

    /**
     *
     * @param request
     * @param username
     * @return
     * @throws NoSuchAlgorithmException
     *
     * Encrypt 이후, DB에 저장시키고 Copyright_id값 가져오는 것까지 작성해놓았습니다.
     * IPFS에 값을 저장하고, RegisterCopyrightReponse에 맞춰 값을 리턴시켜주세요.
     */
    public String registCopyright(RegisterCopyrightRequest request, String username) throws NoSuchAlgorithmException, IOException {
        Optional<User> user = userRepository.findByUsername(username);

        Copyright newCopyright = Copyright.getCopyrightByRequest(request,user.orElseThrow(NoSuchElementException::new));

        MultipartFile multipartFile = fileService.convertJsonToMultipartfile(request, username);
        String hash = ipfsService.saveFile(multipartFile);
        newCopyright.updateCopyrightId(hash);
        return copyrightRepository.save(newCopyright).getCopyrightId();
    }

    public List<RegisterCopyrightResponse> checkSimilarity(String username, RegisterCopyrightRequest request, int threshold){

        String newPrompt = request.getPrompt();
        List<OwnTicketResponseDto> tickets = userService.getTicketsByUsername(username);
        List<RegisterCopyrightResponse> responses = null;

        for(int i=0; i<tickets.size(); i++){
            String hashcode = tickets.get(i).getCopyrightId().getCopyrightId();
            String existCopyrightName = tickets.get(i).getCopyrightId().getCopyrightTitle();

            JSONObject json = new JSONObject(new String(ipfsService.loadFile(hashcode)));
            String existPrompt = json.get("prompt").toString();

            /**
             * existPrompt 는 본래 암호화 되어 있기 때문에,
             * 이곳에 복호화 로직이 추가되어야한다.
             */

            double Similarity = Jaccard.jaccardSimilarity(newPrompt, existPrompt);

            if(Similarity > threshold){
                // 유사도가 높아 등록불가한 사용권
                responses.add(RegisterCopyrightResponse.builder()
                        .copyright_name(existCopyrightName)
                        .similarity(Similarity)
                        .validate(false)
                        .build());
            }else{
                // 유사도 검증에 문제가 없어 등록이 가능한 사용권
                responses.add(RegisterCopyrightResponse.builder()
                        .copyright_name(existCopyrightName)
                        .similarity(Similarity)
                        .validate(true)
                        .build());
            }
        }
        return responses;
    }

    /**
     *  이거이거이거이거이거이거이거이거이거이거이거이거이거이거이거이거이거이거이거
     * @param contract_id
     * @return
     */
    public String getDecryptedPrompt(String contract_id) {
        return "";
    }
}
