#!/usr/bin/env python2

import random


def do_xor(p, k):
	out = ''
	for i in xrange(len(p)):
		out += chr(ord(p[i]) ^ ord(k[i]))
	return out


with open('flag', 'rb') as f1:
	p1 = ''.join(f1.readlines())

with open('secret', 'rb') as f2:
	p2 = ''.join(f2.readlines())

# l = max(len(p1), len(p2))

# key = ''.join([chr(random.randint(0, 256)) for i in xrange(l)])

# c1 = do_xor(p1, key)
# c2 = do_xor(p2, key)
print("p1 : ",p1,len(p1))
print("p2 :",p2,len(p2))
flag1 = "TUCTF{"
secxorflag = do_xor(p1,p2)
sec = do_xor(flag1,secxorflag)
print("secrat : " , sec)
# print("key : ",b)
# print("key2 :",c)
# for j in range(32,128):

# 	for i in range(32,128):
# 		secrat = sec + chr(i) + chr(j)
# 		flag2 = do_xor(secrat,secxorflag)
# 		print(flag2)


