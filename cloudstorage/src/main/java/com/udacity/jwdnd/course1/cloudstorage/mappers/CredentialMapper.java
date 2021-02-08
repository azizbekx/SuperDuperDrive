package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId=#{userId}")
    List<Credential> getCredentialByUserId(Integer userId);

    //username for url (website)
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) " +
            "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    void addCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, key=#{key}, password=#{password}" +
            "WHERE credentialId=#{credentialId}")
    void updateCredential(Integer credentialId, String url, String username,String key, String password);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId =#{credentialId}")
    void deleteCredential(int credentialId);

}
