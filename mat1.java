package com.test1;
import java.io.*;
import java.util.*;
public class mat1 {
    public static void main(String[] args) throws IOException {
        int[][] matrix = createMat(5, 5, 10,100);

        String path_dense = "mat.txt";
//        saveMat(matrix,path_dense);
        String path_sparse = "sparse_mat.txt";
//        saveMat(sparseConvert(matrix),path_sparse);
        int[][] ints = readMat(path_sparse);
        System.out.println(Arrays.deepToString(denseConvert(ints)));
        System.out.println("======");
        System.out.println(Arrays.deepToString(ints));

//        int[][] ints = readMat();
//        System.out.println(Arrays.deepToString(ints));
    }

    public static int[][] sparseConvert(int[][] sparse){
        ArrayList al = new ArrayList();
        int count=1;
        for(int i=0;i<sparse.length;i++){
            for(int j=0;j<sparse[0].length;j++){
                if(sparse[i][j]!=0){
                    int[] cell = {i,j,sparse[i][j]};
                    //obj[count] = cell;
                    al.add(cell);
                    count+=1;
                }
            }
        }
        int[][] obj = new int[count][3];
        obj[0][0] = sparse.length;
        obj[0][1] = sparse[0].length;
        obj[0][2] = count-1;
        for(Object o:al){
            obj[al.indexOf(o)+1]=(int[]) o;
        }
        return obj;
    }

    public static int[][] denseConvert(int[][] dense){
        int[][] obj = new int[dense[0][0]][dense[0][1]];
        for(int i=0;i<dense[0][0];i++){
            for(int j=0;j<dense[0][1];j++){
                obj[i][j] = 0;
            }
        }
        for(int k =1;k<dense.length;k++){
            obj[dense[k][0]][dense[k][1]]=dense[k][2];
        }
        return obj;
    }

    public static int[][] readMat(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        ArrayList al = new ArrayList();
        String str;
        while ((str=br.readLine())!=null){
            int[] num = Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
            al.add(num);
        }
        int[][] result = new int[al.size()][];
        for(int l=0;l<al.size();l++){
            result[l]=(int[])al.get(l);
        }
        return result;
    }

    public static void saveMat(int[][] mat, String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        for(int i=0;i<mat.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<mat[i].length;j++){
                if(j!=mat[i].length-1){
                    sb.append(mat[i][j]).append(",");
                }else{
                    sb.append(mat[i][j]);
                }
            }
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.close();
    }

    public static int[][] createMat(int row,int col,int count,int bound){
        int[][] objMat = new int[row][col];
        int i = objMat.length;
        int j = objMat[i-1].length;
        HashSet<ArrayList> hs = new HashSet<ArrayList>();
        Random rd = new Random();
        while (hs.size()<count){
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(rd.nextInt(row-1));
            al.add(rd.nextInt(col-1));
            hs.add(al);
        }
        for(int r=0;r<row;r++){
//            StringBuilder sb = new StringBuilder();
            for(int c=0;c<col;c++){
                ArrayList<Integer> cmp = new ArrayList<Integer>();
                cmp.add(r);
                cmp.add(c);
                if(hs.contains(cmp)){
                    //objMat[r][c] = 1;
                    objMat[r][c] = (new Random()).nextInt(bound);
                }else{
                    objMat[r][c] = 0;
                }
            }
        }
        //System.out.println(Arrays.deepToString(objMat));
        return objMat;
    }

}