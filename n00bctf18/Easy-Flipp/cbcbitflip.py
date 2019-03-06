from Crypto.Cipher import AES
from Crypto.Util.Padding import pad,unpad
from Crypto.Random import get_random_bytes

greeting = """

         ___    ___   _        ___  _____   ___      _   ___  
 _ __   / _ \  / _ \ | |__    / __\/__   \ / __\    / | / _ \ 
| '_ \ | | | || | | || '_ \  / /     / /\// _\_____ | || (_) |
| | | || |_| || |_| || |_) |/ /___  / /  / / |_____|| | \__, |
|_| |_| \___/  \___/ |_.__/ \____/  \/   \/         |_|   /_/ 
                                                              

"""
line = """                                                                                                            
 _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _  _ 
(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)(_)
"""
key = get_random_bytes(16)
iv = get_random_bytes(16)

flag = open('flag','rb').read().strip()

def xor(a,b):
	return ''.join([chr(ord(a[i])^ord(b[i%len(b)])) for  i in range(len(a))])

def encrypt_data(data):
    cipher = AES.new(key, AES.MODE_CBC,iv)
    enc = cipher.encrypt(pad(data,16,style='pkcs7'))
    return enc.encode('hex')

def decrypt_data(encryptedParams):
    cipher = AES.new(key, AES.MODE_CBC,iv)
    paddedParams = cipher.decrypt(encryptedParams.decode('hex'))
    return unpad(paddedParams,16,style='pkcs7')

print(greeting)
print('hey n00b!! you know how CBC bit flipping works?\nIf you flip the bit correctly i will reward you fl4g!')
print(line)
msg = "admin=0"
print("Current Auth Message is : " + msg)
print("Encryption of auth Message in hex : " + iv.encode('hex') + encrypt_data(msg))
enc_msg = raw_input("Give me Encrypted msg in hex : ")
try:
    final_dec_msg = decrypt_data(enc_msg)

    if "admin=1" in final_dec_msg:
        print('Whoa!! you got it!! Now its reward time!!')
        print(flag)
    else:
        print('Try again you can do it!!')
        exit()
except:
    print('bye bye!!')