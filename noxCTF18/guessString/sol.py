from sympy import isprime

a=[0]*11

for a0 in xrange(32,128):
	for a1 in xrange(32,128):
		if a0*a1==3478:
			a2=a0^a1^49
			for a3 in xrange(a2+1,128):
				if a2**2 % 256 ==a3**2 % 256:
					for a4 in xrange(32,128):
						for a5 in xrange(32,128):
							if isprime(a4) and isprime(a5) and a4^a5==126:
								if (isprime(a5-42)) and 2*(a5-42)<128:
									a6=2*(a5-42)
									for a7 in xrange(48,57,4):
										a8=a7^0x12
										a9=2*a8 % 256
										t = a9*(a9+1)/2

										a10 = t*((t>>8)&0xff) % 256
										if a10 in xrange(32,128):
											a=[a0,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10]
											print (''.join(map(chr,a)))