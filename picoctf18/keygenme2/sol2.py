#!/usr/bin/env python

from pwn import *
from constraint import *

user = ''
pw = ''

debug = 0

problem = Problem()
for i in range(16):
	problem.addVariable('k{}'.format(i), range(25))

problem.addConstraint(lambda a, b: (a + b) % 36 == 14, 			('k0', 'k1')) 			# constraint_01
problem.addConstraint(lambda a, b: (a + b) % 36 == 24, 			('k2', 'k3')) 			# constraint_02
problem.addConstraint(lambda a, b: (b - a + 36) % 36 == 6, 		('k0', 'k2')) 			# constraint_03
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 4, 	('k1', 'k3', 'k5')) 	# constraint_04
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 13, 	('k2', 'k4', 'k6')) 	# constraint_05
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 22, 	('k3', 'k4', 'k5')) 	# constraint_06
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 31, 	('k6', 'k8', 'k10')) 	# constraint_07
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 7, 	('k1', 'k4', 'k7')) 	# constraint_08
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 20, 	('k9', 'k12', 'k15')) 	# constraint_09
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 12, 	('k13', 'k14', 'k15')) 	# constraint_10
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 27, 	('k8', 'k9', 'k10')) 	# constraint_11
problem.addConstraint(lambda a, b, c: (a + b + c) % 36 == 23, 	('k7', 'k12', 'k13')) 	# constraint_12

sol = problem.getSolution()

key = ''
print(sol)
for i in range(16):
	if sol['k{}'.format(i)] < 10:
		key += str(sol['k{}'.format(i)])
	else:
		key += chr(sol['k{}'.format(i)] - 10 + ord('A'))

log.info('Found key: {}'.format(key))

if debug:
	p = process('./activate {}'.format(key), shell=True)
else:
	s = ssh(host = '2018shell1.picoctf.com', user='dr3dd', password='yoyoman12345')
	s.set_working_directory('/problems/keygen-me-2_0_ac2a45bc27456d666f2bbb6921829203')

	p = s.process('./activate {}'.format(key), shell=True)

print(p.recvall())