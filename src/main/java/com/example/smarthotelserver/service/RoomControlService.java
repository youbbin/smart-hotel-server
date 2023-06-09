package com.example.smarthotelserver.service;

import com.example.smarthotelserver.dto.RoomControlDto;
import com.example.smarthotelserver.entity.Room;
import com.example.smarthotelserver.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class RoomControlService {
    private final RoomRepository roomRepository;

    public Object roomControl(RoomControlDto roomControlDto){
        Optional<Room> optionalRoom = roomRepository.findById(roomControlDto.getRoomNumber());
        if(optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setAudioSong(roomControlDto.getAudioSong());
            room.setCeilingLedColor(roomControlDto.getCeilingLedColor());
            room.setCeilingLedPower(roomControlDto.isCeilingLedPower());
            room.setDeskLedPower(roomControlDto.isDeskLedPower());
            room.setAirPurifierPower(roomControlDto.isAirPurifierPower());
            room.setSafeboxOpen(roomControlDto.isSafeboxOpen());
            roomRepository.save(room);
            log.info(roomControlDto.getRoomNumber()+" Control Completed");
            return "Control Completed";
        }
        log.info(roomControlDto.getRoomNumber()+" Control Failed");
        return "Room Number Not Found";
    }
}
