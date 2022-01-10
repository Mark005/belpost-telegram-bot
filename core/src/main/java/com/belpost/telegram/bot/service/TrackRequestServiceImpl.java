package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.repository.ChatTrackRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class TrackRequestServiceImpl implements TrackRequestService {
    private final ChatTrackRequestRepository chatTrackRequestRepository;

    @Override
    public ChatTrackRequest addNewTrackingNumber(ChatTrackRequest request) {
        var duplicates =
                chatTrackRequestRepository.findAllByChatIdAndTrackNumber(
                        request.getName(),
                        request.getTrackingInfo().getTrackNumber());
        if (!CollectionUtils.isEmpty(duplicates)) {
            throw new TrackRequestCreationException(duplicates);
        }
        return chatTrackRequestRepository.save(request);
    }
}
