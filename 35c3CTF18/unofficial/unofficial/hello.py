import glob 
import random

def egcd(a, b):
    if a == 0:
        return (b, 0, 1)
    else:
        g, y, x = egcd(b % a, a)
        return (g, x - (b // a) * y, y)

def modinv(a, m):
    g, x, y = egcd(a, m)
    if g != 1:
        raise Exception('modular inverse does not exist')
    else:
        return x % m

files = glob.glob("data/*01337")

data = []

p = 21652247421304131782679331804390761485569

for f in files:
    with open(f) as fd:
        response = int(fd.read().strip())

    serverf = f.replace("data/", "").split("-")[1] + '-' + f.replace("data/","").split("-")[0]
    with open("data/" + serverf) as fd:
        params = [l.strip() for l in fd.readlines()]
        challenge = list(map(int, params[0].split()))

    if params[1] == "ACCESS GRANTED":
        data.append([challenge, response])

# Rearrange data ready for Gaussian elimination!
l = []
for (coefficients, target) in data:
    l.append(coefficients + [target])

def gaussian_elimination(m, NUMBER_OF_EQUATIONS, NUMBER_OF_COEFFICIENTS):
    assert len(m[0]) == NUMBER_OF_COEFFICIENTS + 1
    assert len(m) == NUMBER_OF_EQUATIONS

    def reduce(target, source, factor):
        m[target] = [(x - factor*y) % p for (x, y) in zip(m[target], m[source])]

    def swap(target, source):
        l = m[target]
        m[target] = m[source]
        m[source] = l

    column = 0
    while column < NUMBER_OF_EQUATIONS:
        if egcd(m[column][column], p)[0] != 1:
            target = random.choice(range(column + 1, NUMBER_OF_EQUATIONS))
            print("uh oh. trying to swap with another... %d,%d" % (column, target))
            swap(column, random.choice(range(column + 1, NUMBER_OF_EQUATIONS)))

            continue

        for j in range(column + 1, NUMBER_OF_EQUATIONS):
            factor = m[j][column] * modinv(m[column][column], p)
            reduce(j, column, factor)
        column += 1

    return m

def solve(equations):
    NUMBER_OF_EQUATIONS = len(equations)
    NUMBER_OF_COEFFICIENTS = len(equations[0]) - 1
    reduced = gaussian_elimination(equations[:], NUMBER_OF_EQUATIONS, NUMBER_OF_COEFFICIENTS)
    
    free_variables = NUMBER_OF_COEFFICIENTS - NUMBER_OF_EQUATIONS
    assert free_variables == 1 # just assuming this is so for simplicity

    solution = ([None] * NUMBER_OF_EQUATIONS) + [(0, 1)]
    for coefficient in range(NUMBER_OF_EQUATIONS - 1, -1, -1):
        row = reduced[coefficient] 
 
        # assert that we're reduced, i.e. in upper triangular form
        if coefficient != 0:
            assert set(row[:coefficient]) == set([0])
        lhs = row[coefficient]
        inverse = modinv(lhs, p)
 
        rhs = (row[-1] % p, 0)
        for other_coefficient in range(coefficient + 1, NUMBER_OF_COEFFICIENTS):
            f = lambda i: rhs[i] - (row[other_coefficient]  * solution[other_coefficient][i])
            rhs = (f(0) % p, f(1) % p)
 
        rhs = (rhs[0] * inverse % p, rhs[1] * inverse % p)
        solution[coefficient] = rhs
        
    a = map(lambda x : x[0], solution)
    b = map(lambda x : x[1], solution)

    # Try asserting that something is actually a solution
    for k in range(200):
        suggested_solution = [(a[i] + k*b[i]) % p for i in xrange(len(a))]
        for eq in equations:
            lhs = sum(suggested_solution[i] * eq[i] for i in range(NUMBER_OF_COEFFICIENTS))
            rhs = eq[-1]
            assert (lhs - rhs) % p == 0
 
    return (a, b)

(a, b) = solve(l)
print a,b