



在我电脑上 Xms=512M Xmx=512M的情况下

# 1.暂停时间

- **Serial GC**

![image](https://i.niupic.com/images/2021/05/13/9hwH.png)


平均STW | 总STW | Min/Max STW
---|---|---
32ms | 640ms | 10/50ms



- **Parallel GC**

![image](https://i.niupic.com/images/2021/05/13/9hwI.png)



- **CMS GC**

![image](https://i.niupic.com/images/2021/05/13/9hwJ.png)


- **G1 GC**

![image](https://i.niupic.com/images/2021/05/13/9hwK.png)



# 2.内存分配

- **Serial GC**

![image](https://i.niupic.com/images/2021/05/13/9hwL.png)

- **Parallel GC**

![image](https://i.niupic.com/images/2021/05/13/9hwM.png)

- **CMS GC**

![image](https://i.niupic.com/images/2021/05/13/9hwN.png)

- **G1 GC**

![image](https://i.niupic.com/images/2021/05/13/9hwO.png)


# 3.GC频率
这里不传日志截图了，太长


- 从串行到并行, 因为并行, GC次数增多，主要是Young GC 增多(10 -> 26),但是平均暂停时间变短(并行和串行之间，不能用总暂停时间比较) 

- 从并行到 CMS, Full GC次数减少(12 -> 6)

- 从CMS 到 G1， Youn GC 增多. Full GC 再次减少(6 -> 0)


# 4.总结


- 1.从串行到G1, GC的平均暂停时间是不断缩短的.


- 2.最大暂停时间是由FUll GC 导致。从串行到G1, Full GC的次数不断减少。


- 3.串行/并行/CMS 都还有年轻代，老年代的划分.
且比例大致为3:7.
G1 GC 由于划分为默认1024k的Region.年轻代，老年代的比例不再固定，而是根据实际分配情况变化。


