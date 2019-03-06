encr_pycrypto = open('pycrypto-2.6.1.tar.gz.enc','rb')
encr_pycrypto = encr_pycrypto.read().strip()

pycrypto = open('pycrypto-2.6.1.tar.gz','rb')
pycrypto = pycrypto.read().strip()

flag_enc = open('text.txt.enc','rb')
flag_enc = flag_enc.read().strip()

def xor(a,b):
    return ''.join(chr(ord(x)^ord(y)) for x,y in zip(a,b))

x = xor(flag_enc,encr_pycrypto)
flag = xor(x,pycrypto)
print(flag)