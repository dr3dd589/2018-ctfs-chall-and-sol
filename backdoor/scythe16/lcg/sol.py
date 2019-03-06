from functools import reduce
from gmpy2 import gcd

def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, x, y = egcd(b % a, a)
        return (g, y - (b // a) * x, x)

def modinv(b, n):
    g, x, _ = egcd(b, n)
    if g == 1:
        return x % n

def crack_unknown_increment(states, modulus, multiplier):
    increment = (states[1] - states[0]*multiplier) % modulus
    return modulus, multiplier, increment

def crack_unknown_multiplier(states, modulus):
    multiplier = (states[2] - states[1]) * modinv(states[1] - states[0], modulus) % modulus
    return crack_unknown_increment(states, modulus, multiplier)

def crack_unknown_modulus(states):
    diffs = [s1 - s0 for s0, s1 in zip(states, states[1:])]
    zeroes = [t2*t0 - t1*t1 for t0, t1, t2 in zip(diffs, diffs[1:], diffs[2:])]
    modulus = abs(reduce(gcd, zeroes))
    return crack_unknown_multiplier(states, modulus)

def xor(a,b):
    flag = ""
    for i in range(len(cipher)):
        flag += chr(ord(b[i])^ord(a[i%len(a)]))
    return flag

obs = [7038329, 2595920584582988203224023874, 7475933611109024100933403119814559969, 31747476908195446927119842689781944834, 56414337241223827095371416459206162677, 99394688082857206064008418768140322102, 109367624086937873413279568187024041009, 40725846811506101619261190275112585346, 110494169997775806409950259924752648741, 52602757756756180636420012361374566534]

n,k,d = crack_unknown_modulus(obs)
state = obs[-1]

cipher = "4cf20a8068d0f7eb60f1922ec016ef6844b44bc368cbf7e7".decode('hex')
key = ""
for i in range(5):
    try:
        state = (state*k+d) % n
        key = hex(state)[2:].decode('hex')
        print(xor(key,cipher),i,len(cipher))
    except:
        continue

    