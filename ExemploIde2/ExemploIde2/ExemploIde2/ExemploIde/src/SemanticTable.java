/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



public class SemanticTable {
   
   public static final int ERR = -1;
   public static final int OK_ = 0;
   public static final int WAR = 1;


   public static final int INT = 0;
   public static final int FLO = 1;
   public static final int STR = 2;
   public static final int BOO = 3;

   public static final int SUM = 0;
   public static final int SUB = 1;
   public static final int MUL = 2;
   public static final int DIV = 3;
   public static final int REL = 4; // qualquer operador relacional

   // TIPO DE RETORNO DAS EXPRESSOES ENTRE TIPOS
   // 5 x 5 X 5  = TIPO X TIPO X OPER
   static int expTable [][][] = 
                {  /*     INT       */ /*       FLOAT    */  /*    STRING    */     /*     BOOL        */
    /*INT*/     {{INT,INT,INT,FLO,BOO},{FLO,FLO,FLO,FLO,BOO},{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,ERR}},
    /*FLOAT*/   {{FLO,FLO,FLO,FLO,BOO},{FLO,FLO,FLO,FLO,BOO},{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,ERR}}, 
    /*STRING*/  {{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,ERR},{STR,ERR,ERR,ERR,BOO},{ERR,ERR,ERR,ERR,ERR}},
    /*BOOL*/    {{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,ERR},{ERR,ERR,ERR,ERR,BOO}}                        
                };
   
   // atribuicoes compativeis 
   // 5 x 5 = TIPO X TIPO
   static int atribTable [][]={/* INT FLO STR BOO  */
   /*INT*/                {OK_,WAR,ERR,ERR},
   /*FLO*/                {OK_,OK_,ERR,ERR},
   /*STR*/                {ERR,ERR,OK_,ERR},
   /*BOO*/                {ERR,ERR,ERR,OK_}
                         };
   /*
    String resultType (String TP1, String TP2, String OP){
        System.out.println("Param recebido: "+TP1+" - "+TP2+" - "+OP);
        int x=-1,y=-1,z=-1;
      if(TP1.equals("int") || TP1.equals("DEC") || TP1.equals("HEXA") || TP1.equals("BIN")){
          x=0;
      }
      if(TP1.equals("bool") || TP1.equals("BOOL")){
          x=3;
      }
      if(TP1.equals("REAL") || TP1.equals("real")){
          x=1;
      }
      if(TP1.equals("TEXTO") || TP1.equals("string")){
          x=2;
      }
      if(TP2.equals("int") || TP2.equals("DEC") || TP2.equals("HEXA") || TP2.equals("BIN")){
          y=0;
      }
      if(TP2.equals("bool") || TP2.equals("BOOL")){
          y=3;
      }
      if(TP2.equals("REAL") || TP2.equals("real")){
          y=1;
      }
      if(TP2.equals("TEXTO") || TP2.equals("string")){
          y=2;
      }
      if(OP.equals("+")){
          z=0;
      }
      if(OP.equals("-")){
          z=1;
      }
      if(OP.equals("*")){
          z=2;
      }
      if(OP.equals("/")){
          z=3;
      }
      if(OP.equals(">") || OP.equals("<") || OP.equals("==") || OP.equals("<=") || OP.equals(">=")){
          z=4;
      }
       System.out.println("Param recebido: "+x+" - "+y+" - "+z);
      int resultado = expTable[x][y][z];
      String result = "";
      switch(resultado){
          case -1: result="ERR";
              break;
          case 0: result="DEC";
              break;
          case 1: result="REAL";
              break;
          case 2: result="TEXTO";
              break;
          case 3: result="BOOL";
              break;
      }
      return result;
   }
   */
   public static int resultType(int TP1, int TP2, int OP) {
       return expTable[TP1][TP2][OP];
   }
   static public int atribType(int ESQ, int DIR) {
       //System.out.println(ESQ+" -ESQ E DIR-" + DIR);
       return atribTable[ESQ][DIR];
   }
   
    String atribType (String TP1, String TP2){
      int x=-1,y=-1,z=-1;
      if(TP1.equals("int") || TP1.equals("DEC") || TP1.equals("HEXA") || TP1.equals("BIN")){
          x=0;
      }
      if(TP1.equals("BOOL") || TP1.equals("bool")){
          x=3;
      }
      if(TP1.equals("REAL") || TP1.equals("real")){
          x=1;
      }
      if(TP1.equals("TEXTO") || TP1.equals("string")){
          x=2;
      }
      if(TP2.equals("int") || TP2.equals("DEC") || TP2.equals("HEXA") || TP2.equals("BIN")){
          y=0;
      }
      if(TP2.equals("BOOL") || TP2.equals("bool")){
          y=3;
      }
      if(TP2.equals("REAL") || TP2.equals("real")){
          y=1;
      }
      if(TP2.equals("TEXTO") || TP2.equals("string")){
          y=2;
      }
      int resultado = atribTable[x][y];
      String result = "";
      switch(resultado){
          case -1: result="ERR";
              break;
          case 0: result="OK_";
              break;
          case 1: result="WAR";
              break;
      }
      
      return result;
   }
}
