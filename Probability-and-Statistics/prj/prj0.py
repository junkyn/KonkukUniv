from traitlets.traitlets import List
from numpy.lib.shape_base import kron
import random
import matplotlib.pyplot as plt
import numpy as np

k = int(input("Input K: "))
list = [0] * k
xlist = ['0']*k
pmflist = [0]*100
ylist = [0]*k
sum = 0
for i in range(k):
  xlist[i]=str(i+1)
for i in range(10):
    tmp = random.randint(1, k)
    list[tmp-1] += 1
print("랜덤 확률 생성")
for i in range(k):
    list[i]/=10
    sum+=list[i]
    print(list[i],end=" ")
    if(i!=k-1):
      print('+',end=" ")
    else:
      print('=',end=" ")
    list[i]*=100
print(sum)
sum = 0
plt.bar(xlist,list)
plt.title('Given probability')
plt.show()
i = 0
for j in range(k):
  for o in range(int(list[j])):
    pmflist[i] = j+1
    i+=1
for i in range(10000):
  idx = random.randint(0,99)
  ylist[pmflist[idx]-1]+=1
plt.bar(xlist,ylist)
plt.title('Result')
plt.show()