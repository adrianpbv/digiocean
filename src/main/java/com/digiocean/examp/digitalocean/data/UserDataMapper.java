package com.digiocean.examp.digitalocean.data;

public class UserDataMapper {
    public static UserDemoDto toDto(UserDemoEntity entity) {
        UserDemoDto dto = new UserDemoDto();
        dto.setName(entity.getUsername());
        dto.setAge(entity.getAge());
        return dto;
    }
}
