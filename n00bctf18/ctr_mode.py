from Crypto.Cipher import AES
from Crypto.Util.Padding import pad,unpad
from Crypto.Random import get_random_bytes
import random



key = get_random_bytes(16)
nonce = get_random_bytes(16)

def xor(a,b):
	return ''.join([chr(ord(a[i])^ord(b[i%len(b)])) for  i in range(len(a))])

def encrypt_data(data):
    cipher = AES.new(key, AES.MODE_CBC,iv)
    enc = cipher.encrypt(pad(data,16,style='pkcs7'))
    return enc

def decrypt_data(encryptedParams):
    cipher = AES.new(key, AES.MODE_CBC,iv)
    paddedParams = cipher.decrypt(encryptedParams)
    try:
        params = unpad(paddedParams,16,style='pkcs7')
        return "True"
    except:
        return "False"