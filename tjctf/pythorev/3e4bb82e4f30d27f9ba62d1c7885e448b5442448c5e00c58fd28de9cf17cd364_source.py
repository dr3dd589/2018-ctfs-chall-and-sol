# import numpy as np

# flag = 'ABCDEF'

# np.random.seed(12345)
# arr = np.array([ord(c) for c in flag])
# other = np.random.randint(1,5,(len(flag)))
# print(arr,other)
# arr = np.multiply(arr,other)
# print(arr)
# b = [x for x in arr]
# lmao = [ord(x) for x in ''.join(['ligma_sugma_sugondese_'*5])]
# c = [b[i]^lmao[i] for i,j in enumerate(b)]
# print(''.join(bin(x)[2:].zfill(8) for x in c))

import numpy as np
import string

flag = ""

for y in range(15):
	for x in string.printable:
		np.random.seed(12345)
		sav = flag + x
		arr = np.array([ord(c) for c in sav])
		other = np.random.randint(1,5,(len(sav)))
		arr = np.multiply(arr,other)

		b = [x for x in arr]
		lmao = [ord(x) for x in ''.join(['ligma_sugma_sugondese_'*5])]
		c = [b[i]^lmao[i] for i,j in enumerate(b)]
		bla = ''.join(bin(x)[2:].zfill(8) for x in c)

		out = "1001100001011110110100001100001010000011110101001100100011101111110100011111010101010000000110000011101101110000101111101010111011100101000011011010110010100001100010001010101001100001110110100110011101"

		size = len(bla)
        if out[:size] == bla:
            flag += x 
            print(flag)       


print(flag)