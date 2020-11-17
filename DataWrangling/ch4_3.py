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

#计算二元二次多项式 z=sqrt(x^2+y^2) 的值,并尝试可视化
z = np.sqrt(xs ** 2 + ys ** 2)
print("z:")
print(z)
print(z.shape)
#可视化
plt.imshow(z, cmap=plt.cm.gray); plt.colorbar()

plt.title("Image plot of $\sqrt{x^2}+y^2$ for a grid of values")
# %%
import numpy as np
import matplotlib.pyplot as plt
%matplotlib inline
#numpy.where操作对应于三元表达式 x if condition else y的向量版本
xarr = np.array([1.1, 1.2, 1.3, 1.4, 1.5])
yarr = np.array([2.1, 2.2, 2.3, 2.4, 2.5])
cond = np.array([True,False,True,True,False])
result = np.where(cond,xarr,yarr)
print(result)
# %%
