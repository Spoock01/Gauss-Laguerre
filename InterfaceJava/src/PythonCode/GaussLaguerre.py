import numpy.polynomial.laguerre as qdt
import numpy as np
import math
from scipy.integrate.quadpack import quad
import sys

N = 0

if len(sys.argv) >= 1:
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

def integrand(x):
    return math.sin(x) * math.exp(-x) * 4


print('Real:', quad(integrand, 0, np.inf)[0])

# print('\n\n\nSample points: ', sample_points)
# print('weight points: ', weight_points)

mysum = 0

for i in range(N):
    # print('Sin({}) * Weight({}) * 4'.format(sample_points[i], weight_points[i]))
    mysum += math.sin(sample_points[i]) * weight_points[i] * 4

print('Laguerre:', mysum)


