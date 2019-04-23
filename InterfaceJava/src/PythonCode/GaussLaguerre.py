import numpy.polynomial.laguerre as qdt
import numpy as np
import math
import math.exp as exp
from scipy.integrate import quad

N = 6

sample_points, weight_points = qdt.laggauss(N)

# print('Sample points', sample_points)
# print('Weight points', weight_points)
#
# custom_pts = qdt.lagweight(np.array(ar))
# print('Custom points', custom_pts)
# #
# # for i in range(len(custom_pts)):
# #     print(-custom_pts[i] + sample_points[i])
#
# print(np.exp(-0.8776120886911798))
# #
# #
# # print(math.log(0.41577456))


def integrand(x):
    return math.sin(x) * exp(-x) * 4


print('Resultado da integral: ', quad(integrand, 0, np.inf)[0])

# print('\n\n\nSample points: ', sample_points)
# print('weight points: ', weight_points)

mysum = 0

for i in range(N):
    # print('Sin({}) * Weight({}) * 4'.format(sample_points[i], weight_points[i]))
    mysum += math.sin(sample_points[i]) * weight_points[i] * 4

print('Resultado com GaussLaguerre: ', mysum)

