import ast
from pwn import *

r = remote('200.136.252.51', 5555)
r.recvuntil('[3] Exit\n')
r.sendline('1')
r.recvuntil('Username: ')
r.sendline('abc')
r.recvuntil('Password: ')
r.sendline('abc')
r.recvuntil('\n')
token = r.recvuntil('\n').strip()
token = ast.literal_eval(token)
r.close()

enc = token['enc'].decode('hex')
enc = enc[:30] + chr(ord(enc[30:31])^ord('N')^ord('Y')) + enc[31:]
token['enc'] = enc.encode('hex')

def check():
    tok = token
    for i in range(256):
        print('i is ......................',i)
        tok['tag'] = chr(i).encode('hex')
        r = remote('200.136.252.51', 5555)
        r.recvuntil('[3] Exit\n')
        r.sendline('2')
        r.recvuntil('Token: ')
        r.sendline(str(tok))
        print(str(tok))
        res = r.recvall()
        if 'InvalidTag' not in res:
            print(i, res)
            r.close()
            break
        r.close()


if __name__=='__main__':
    check()