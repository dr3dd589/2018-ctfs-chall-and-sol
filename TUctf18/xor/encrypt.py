#!/usr/bin/env python2

def xor(msg, key):
    o = ''
    for i in range(len(msg)):
        o += chr(ord(msg[i]) ^ ord(key[i % len(key)]))
    return o

with open('encrypted', 'r') as f:
    msg = ''.join(f.readlines()).rstrip('\n')

flag = "XORISCOOL"

print(xor(msg,flag))