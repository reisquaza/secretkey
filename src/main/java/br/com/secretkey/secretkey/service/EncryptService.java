package br.com.secretkey.secretkey.service;

import javax.crypto.SecretKey;

public interface EncryptService {
    String encrypt(String strToEncrypt);
    String decrypt(String strToDecrypt);
}
