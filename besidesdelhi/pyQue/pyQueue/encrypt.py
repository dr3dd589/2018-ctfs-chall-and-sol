#!/usr/bin/env python2
import os
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes
import string

# FLAG = "flag{....AAAABBBBCCCCDDDDEEEEFFFFGGGGHHHH....}"


class AES_Key:
    def __init__(self):
        self.key=list(os.urandom(16))

    def enqueue(self):
        self.key+=get_random_bytes(1)

    def dequeue(self):
        self.key=self.key[1:]

    def size(self):
        return len(self.key)

    def shuffle(self):
        self.dequeue()
        self.enqueue()
        assert self.size()==AES.block_size
        return "".join(self.key)

ct = "6ecae0c59d1e71e14e1fe799561b841126d0f9a820cf6a12355476400b07c4ca0912fc4fa12588ace67cb689f8f94643".decode('hex')
def pad(msg):
    pad_byte = 16 - len(msg) % 16
    return msg + chr(pad_byte) * pad_byte

def slice(msg,step = AES.block_size):
    yield [pad(msg)[i:i+step] for i in range(0,len(msg),step)]

final_key = list("\xe3q>\xb0\xda\xfa>`\xc0\xbb\xdc\xb6\xff\xf4\x8eb")
key = AES_Key()
dec = ""
MAC = 0
flag = []
for j in range(0,2,16):
    final_key.pop(15)
    for i in range(256):
        k = final_key
        k.insert(0,chr(i))
        x = ''.join(k)
        m = AES.new(x,AES.MODE_ECB)
        msg = m.decrypt(ct[-32:-16])
        d = 0
        print(msg,len(msg))
        for c in msg:
            if c in string.printable:
                d +=1
        if d >= 12:
            flag.append(msg)
            final_key = k
            break
        else:
            k.pop(0)
print(flag)
# for List in slice(ct):
#     for block in List:
#         print(key.shuffle(),'..........')
        # print(block,len(block),key.shuffle())
        #cipher = AES.new(key.shuffle(), AES.MODE_ECB)
        #ct+= cipher.encrypt(block)
        # dec += block
        # MAC ^= int(dec[-16:].encode('hex'),16)

# a = hex(MAC^215967656713349787396273448144015034618)[2:-1]
# key = a.decode('hex')
# print(key,len(key))
#MAC ^= int(key.shuffle().encode('hex'),16)

# open("ci.pher.text",'wb').write(str(MAC) +":"+ ct.encode('hex'))



