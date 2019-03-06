from pwn import *
import string
 
def choose_XOR_op(input):
    for i in input:
        assert i in string.hexdigits
    r.recvuntil("*")
    r.sendline("XOR")
    r.recvuntil(">>")
    r.sendline(input)
    ct = r.recvline()[17:].strip()
    unknown = r.recvline().strip()
    return ct, unknown
 
def choose_ADD_op(input):
    for i in input:
        assert i in string.hexdigits
    r.recvuntil("*")
    r.sendline("ADD")
    r.recvuntil(">>")
    r.sendline(input)
    ct = r.recvline()[17:].strip()
    unknown = r.recvline().strip()
    return ct, unknown
 
def choose_DEC_op(input):
    r.recvuntil("*")
    r.sendline("DEC")
    r.recvuntil(">>")
    r.sendline(input)
    print r.recvline()
    print r.recvline()
    r.close()
 
list1 = [2**i for i in range(128)]
key = ""
 
r = remote("arcade.fluxfingers.net","1821")
for i in list1:
    ct, unknown = choose_XOR_op(hex(i)[2:].replace("L",""))
    ct1, unknown1 = choose_ADD_op(hex(i)[2:].replace("L",""))
    if ct+unknown == ct1+unknown1:
        key += "0"
        print key
    else:
        key += "1"
        print key
 
key = hex(int(key[::-1], 2))[2:].replace("L","").decode("hex").encode("base64").replace("\n","")
choose_DEC_op(key)
 
r.close()