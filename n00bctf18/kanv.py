import numpy as np
flag = "flag{dummy_flag}"

#Generating the key
secret_big_number = 24124282839486546465484688464684545467521672612674216412642465246256425645264526452462431295779579254326752135265676523
key_seed = np.random.rand(10)
key = [0,0,0,0,0,0,0,0,0,0]
for i in range(10):
	key[i] = int(np.floor(secret_big_number*key_seed[i]))

print("Let's Play a game. If you are able to solve my challenge, I will give you the flag."
		"I will generate a random polynomial p(x). And give you values of the polynomial at any two values of x you choose."
		"Then I will ask you for the value of the polynomial at a point of my choice. Are you ready! ")
print("Enter your first try:")
x = 1
a = input()
s = 0
for i in range(10):
	s = s + x*key[i]
	x = x*a
print("The value of f({}) is {}".format(a,s))

print("Give me your next try:")
x = 1
a = input()
s = 0
for i in range(10):
	s = s + x*key[i]
	x = x*a
print("The value of f({}) is {}".format(a,s))

x = 1
a = np.random.randint(1,100)
s = 0
for i in range(10):
	s = s + x*key[i]
	x = x*a
print("Its my turn now. Give me the value of f({}):".format(a))
value = input()
if value == s:
	print("So you got me! Here's your flag :{}".format(flag))
else:
	print("Not that easy!")