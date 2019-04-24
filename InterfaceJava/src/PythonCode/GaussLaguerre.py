import numpy.polynomial.laguerre as qdt
import numpy as np
import math
from scipy.integrate.quadpack import quad
import sys

N = 0

def integrandEquacao1(x):
    return math.cos(x) * math.exp(-x)

def integrandEquacao2(x):
    return math.exp(-x) * ((x + 1)**2) * math.exp(-1)

if len(sys.argv) > 2:
    N = int(sys.argv[1])

k = 1
for i in range(1, N + 1):
	sample_points, weight_points = qdt.laggauss(i)
	for j in range(i):
		if j == 0:
			print("{},{},{}".format(k, sample_points[j], weight_points[j]))
		else:
			print("{},{},{}".format('', sample_points[j], weight_points[j]))
	k +=1


sample_points, weight_points = qdt.laggauss(N)

mysum = 0

if int(sys.argv[2]) == 1:
	print('Real:', quad(integrandEquacao1, 0, np.inf)[0])
	for i in range(N):
		mysum += math.cos(sample_points[i]) * weight_points[i]

elif int(sys.argv[2]) == 2:
	print('Real:', quad(integrandEquacao2, 0, np.inf)[0])
	for i in range(N):
		mysum += ((sample_points[i] + 1)**2) * weight_points[i] * math.exp(-1)

print('Laguerre:', mysum)
