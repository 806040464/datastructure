# datastructure
数据结构学习代码

$$
程序=数据结构+算法
$$



数据结构是算法的基础，数据结构是一门研究组织数据方式的学科，有了编程语言就有了数据结构，学号数据结构可以编写出更漂亮更有效率的代码

数据结构包括：**线性结构**和**非线性结构**



## 线性结构

1. 线性结构作为最常用的数据结构，其特点是**数据元素之间存在一对一**的线性关系
2. 线性结构有两种不同的存储结构，即**顺序存储结构（数组）和链式存储结构（链表）**。顺序存储的线性表为顺序表，顺序表中的存储元素是连续的
3. 链式存储的线性表称为链表，链表中的**存储元素不一定是连续的**，元素节点中存放数据元素以及相邻元素的地址信息（充分利用内存碎片）

**线性结构**常见的有：**数组、队列、链表和栈**

**非线性结构**包括：二维数组、多维数组、广义表、树结构、图结构



## 稀疏数组（SparseArray）

当一个数组中大部分元素为0，或者同一个值得数组时，可以使用稀疏数组来保存该数组

稀疏数组的处理方法：

1. 记录数组一共几行几列，有多少不同值

2. 吧具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模

   

### 应用实例

1. 使用稀疏数组来保留二维数组（棋盘、地图等）
2. 把稀疏数组存盘，并可以恢复原来二维数组


![image-20200401234858383.png](https://upload-images.jianshu.io/upload_images/19436361-6cad74fecfdee881.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 代码实现

```
public static void main(String[] args) {
        //创建棋盘原始数组
        //0：表示没有棋子；1：表示白棋；2：表示黑棋
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("原始数组：");
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        //二维数组转为稀疏数组
        //1、遍历原始的二维数组，得到有效数据的个数sum
        //2、根据sum就可创建稀疏数组sparsearray int[sum+1][3]
        //3、将二维数组有效数据存入稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        //将稀疏数组存入文件
        File file = new File("C:\\Users\\Desktop\\data.data");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray.length; j++) {
                    fos.write((sparseArray[i][j] + "\t").getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //将稀疏数组从文件中读取
        int[][] sparseArrayToFile = new int[sum + 1][3];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Desktop\\data.data"));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                for (int i = 0; i < split.length; i++) {
                    sparseArrayToFile[row][i] = Integer.parseInt(split[i]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("从文件读取稀疏数组：");
        for (int i = 0; i < sparseArrayToFile.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArrayToFile[i][0], sparseArrayToFile[i][1], sparseArrayToFile[i][2]);
        }
        //稀疏数组转二维数组
        //1、读取稀疏数组第一行，创建原始二维数组
        //2、读取稀疏数组后几行数据并赋值给原始数组即可
        int[][] ints = new int[sparseArrayToFile[0][0]][sparseArrayToFile[0][1]];
        for (int i = 1; i <= sparseArrayToFile[0][2]; i++) {
            ints[sparseArrayToFile[i][0]][sparseArrayToFile[i][1]] = sparseArrayToFile[i][2];
        }
        System.out.println("从文件读取稀疏数组转二维数组：");
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }
```

