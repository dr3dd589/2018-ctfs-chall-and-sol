#!/usr/bin/env python3
import os, random
from Crypto.Cipher import AES

aes = AES.new(os.urandom(16), AES.MODE_ECB)
enc = lambda x: aes.encrypt(x.to_bytes(16, 'big')).hex()
dec = lambda y: int.from_bytes(aes.decrypt(bytes.fromhex(y)), 'big')

p = 0xfffffed83c17          # |G| = p
mul = lambda x, y: (x+y)%p  # g^x * g^y = g^(x+y)
inv = lambda x: (-x)%p      # (g^x)^-1  = g^(-x)
dhp = lambda x, y: x*y%p    # enjoy your oracle!

g = 1
y = random.randrange(p)
print(y, file=__import__('sys').stderr)

print(enc(g), enc(y))

for _ in range(0x400):
    try:
        q = input().strip().split()
    except EOFError: break
    if q[0] == 'mul':
        print(enc(mul(*map(dec, q[1:]))))
    if q[0] == 'inv':
        print(enc(inv(dec(q[1]))))
    if q[0] == 'dhp':
        print(enc(dhp(*map(dec, q[1:]))))
    if q[0] == 'sol':
        if int(q[1]) % p == y:
            print(open('flag.txt').read().strip())
        break

