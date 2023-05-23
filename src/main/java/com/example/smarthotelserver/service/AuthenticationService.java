package com.example.smarthotelserver.service;

import com.example.smarthotelserver.dto.AuthenticationResponseDto;
import com.example.smarthotelserver.entity.Room;
import com.example.smarthotelserver.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoomRepository roomRepository;

    public Object authenticateByRfidId(String rfidId){
        Optional<Room> optionalRoom = roomRepository.findByRfidId(rfidId);
        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            log.info(room.getRoomNumber().toString());
//            return AuthenticationResponseDto.builder()
//                    .roomNumber(room.getRoomNumber())
//                    .guestName(room.getGuestName())
//                    .phoneNumber(room.getPhoneNumber())
//                    .build();
            return AuthenticationResponseDto.builder()
                                            .roomNumber(room.getRoomNumber())
                                            .guestName(room.getGuestName())
                                            .phoneNumber(room.getPhoneNumber())
                                            .build();
        }
        return "RFID UID Not Found";
    }
}