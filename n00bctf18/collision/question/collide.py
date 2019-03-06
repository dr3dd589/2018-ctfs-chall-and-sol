import binascii
import sys
from itertools import permutations

x = 'abcdefghij'


perms = []


for c in permutations(x, 7):
	perms.append("".join(c))


print("Hello Noob I always like to collide with people or whatever it is! Help me and I will reward you the precious flag" )



for c in perms:
	for i in range(len(perms)):
		input1=c
		input2=perms[i]
		print("."),

		if(input1 == input2):
			print("n00by_n00b_th1nk_he_1s_t00_5m4r7_XD")
			continue
		if(len(input1) and len(input2) <= 5):
			sys.exit('length is too small!!!')
		def calculate_Hash(text):
			var1 = bin(int(binascii.hexlify(text),16))[2:]
			var2 = var1[1::2]
			var3 = var1[-1:]
			var4 = int(var2,2) ^ int(var3,2)
			var5 = '{0:b}'.format(var4)
			var6 = var5[-3:]+var5[0]
			var7 = int(var6, 2)
			var8 = n00b_confused(var7)
			return var8
			
		def n00b_confused(n):
			s = 0
			while n:
				s += n % 10
				n //= 10
			return s

			

		out1 = calculate_Hash(input1)
		out2 = calculate_Hash(input2)

		if( out1 == out2):
			f = open("flag.txt","r")
			print f.read()
			sys.exit("Input1 : " + input1 +"  Input2 : "+ input2)
		else:
			print("Try a bit harder n00b")
