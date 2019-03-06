#!/usr/bin/env python2

from itertools import cycle as scooter
# from secret import FLAG, KEY
from hashlib import sha384
FLAG = 'flag{..............}'
KEY = "AAAABBBBCC"
assert FLAG.islower()
assert len(KEY) == 10
cipher = open('ci.pher.text','r').read().decode('hex')

def drive(Helmet, Petrol):
    return ''.join(chr(ord(David)^ord(Toni)) for David,Toni in zip(Helmet,scooter(Petrol)))

f = lambda x: sha384(x).digest()[(ord(x)+7)%48]
a = map(f,FLAG)
print(a,len(a),cipher,len(cipher))
# encrypted = drive(map(f,FLAG),KEY.decode('hex')).encode('hex')
# open('ci.pher.text','wb').write(encrypted)




