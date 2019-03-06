# uncompyle6 version 3.2.3
# Python bytecode 2.7 (62211)
# Decompiled from: Python 2.7.15rc1 (default, Apr 15 2018, 21:51:34) 
# [GCC 7.3.0]
# Embedded file name: ransomware.py
# Compiled at: 2018-09-04 19:05:11
import string
from random import *
import itertools

englishLetterFreq = {'E': 12.70, 'T': 9.06, 'A': 8.17, 'O': 7.51, 'I': 6.97, 'N': 6.75, 'S': 6.33, 'H': 6.09, 'R': 5.99, 'D': 4.25, 'L': 4.03, 'C': 2.78, 'U': 2.76, 'M': 2.41, 'W': 2.36, 'F': 2.23, 'G': 2.02, 'Y': 1.97, 'P': 1.93, 'B': 1.29, 'V': 0.98, 'K': 0.77, 'J': 0.15, 'X': 0.15, 'Q': 0.10, 'Z': 0.07,'e': 12.70, 't': 9.06, 'a': 8.17, 'o': 7.51, 'i': 6.97, 'n': 6.75, 's': 6.33, 'h': 6.09, 'r': 5.99, 'd': 4.25, 'l': 4.03, 'c': 2.78, 'u': 2.76, 'm': 2.41, 'w': 2.36, 'f': 2.23, 'g': 2.02, 'y': 1.97, 'p': 1.93, 'b': 1.29, 'v': 0.98, 'k': 0.77, 'j': 0.15, 'x': 0.15, 'q': 0.10, 'z': 0.07}

def caesar_cipher(buf, password):
    password = password * (len(buf) / len(password) + 1)
    return ('').join((chr(ord(a) ^ ord(b)) for a, b in itertools.izip(buf, password)))


f = open('enc.txt', 'r')
buf = f.read()
f.close()

allchar = string.ascii_letters + string.punctuation + string.digits

a = []
b = []
x =  ""
for i in range(0,10740,60):
    a.append(buf[i:i+60])

for i in range(60):
    for j in range(179):
        x += str(a[j][i])
    b.append(x)

pass_word = ""

for i in range(60):
    d = [] 
    count = []   
    for c in allchar:
        d.append(caesar_cipher(b[i],c))
    for f in d:
        m = 0
        for j in range(len(f)):
            if f[j] in englishLetterFreq:
                m += englishLetterFreq[f[j]]
        count.append(m)
    y = count.index(max(count))
    pass_word += caesar_cipher(b[i],d[y])[0]
print(pass_word,len(pass_word))
# print(count,len(count))
#key = """:P-@uSL"Y1K$[X)fg[|".45Yq9i>eV)<0C:('q4nP[hGd/EeX+E7,2O"+:[2"""
z = caesar_cipher(buf,pass_word)
file =  open('out.pdf' ,'w')
file.write(z)
file.close()

