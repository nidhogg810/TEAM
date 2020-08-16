#面向向量编程
#%%
import numpy as np
import matplotlib.pyplot as plt
%matplotlib inline
#np.meshgrid接收两个一维数组，根据两个数组的所有(x,y)对生成一个二维矩阵
points = np.arange(-5,5,1)
print(points)
#points 1000个元素
#其中矩阵X的行向量是向量x的简单复制，而矩阵Y的列向量是向量y的简单复制(注：下面代码中X和Y均是数组，在文中统一称为矩阵了)。
#假设x是长度为m的向量，y是长度为n的向量，则最终生成的矩阵X和Y的维度都是 n*m （注意不是m*n）。
xs, ys=np.meshgrid(points,points)
print("ys:")
print(ys)
print("xs:")
print(xs)
#ys 1000*1000个元素
print(xs.shape)
# %%
