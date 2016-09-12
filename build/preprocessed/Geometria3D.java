/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Bionick
 */
public class Geometria3D extends MIDlet implements CommandListener {

    Display d;
    Image img;
    Command salir, pintar, atras, ver;
    Pintar p;
    Form CuboPrismaCilindro, ConoPiramide, Esfera;
    TextField Radio, NumLados, Altura;
    List menu;
    float Rad, Altu;
    int NumLad, tipoSend, desfa;

    public Geometria3D() {
        d = Display.getDisplay(this);
        menu = new List("-= F I G U R A S =-", List.IMPLICIT);
        menu.insert(0, "Cubo", null);
        menu.insert(1, "Prisma", null);
        menu.insert(2, "Cilindro", null);
        menu.insert(3, "Pirámide", null);
        menu.insert(4, "Cono", null);
        menu.insert(5, "Esfera", null);

        atras = new Command("Atrás", Command.BACK, 2);
        ver = new Command("Ver", Command.OK, 2);
        salir = new Command("Salir", Command.EXIT, 1);
        menu.addCommand(salir);
        menu.setCommandListener(this);
    }

    public void startApp() {
        d.setCurrent(menu);
    }

    /**
     * Crea un formulario para los datos de un cono o esfera.
     *
     * @param tipo Tipo de figura.<br>
     * [1] Menu para piramide.<br>
     * [2] Menu para cono.<br>
     * [3] Menu para piramide desfasada.<br>
     * [4] Menu para cono desfasado.<br>
     *
     * @return
     */
    public Displayable conoPiramide(int tipo) {
        ConoPiramide = new Form("Ingrese los datos que se piden:");
        ConoPiramide.addCommand(atras);
        ConoPiramide.addCommand(ver);
        ConoPiramide.setCommandListener(this);
        NumLados = new TextField("Numero de lados: ", "0", 5, TextField.NUMERIC);
        Radio = new TextField("Radio: ", "0", 5, TextField.NUMERIC);
        Altura = new TextField("Altura: ", "0", 5, TextField.NUMERIC);
        if (tipo == 1) {
            // Menu piramide
            ConoPiramide.append(NumLados);
            ConoPiramide.append(Radio);
            ConoPiramide.append(Altura);
            tipoSend = 4;
        } else if (tipo == 2) {
            // Menu Cono
            ConoPiramide.append(Radio);
            ConoPiramide.append(Altura);
            tipoSend = 5;
        } else if (tipo == 3) {
            // Menu piramide desfasada
            ConoPiramide.append(NumLados);
            ConoPiramide.append(Radio);
            ConoPiramide.append(Altura);
            tipoSend = 10;
            desfa = 1;
        } else if (tipo == 4) {
            // Menu Cono desfasado
            ConoPiramide.append(Radio);
            ConoPiramide.append(Altura);
            tipoSend = 11;
            desfa = 1;
        }
        return ConoPiramide;
    }

    /**
     * Crea un formulario para los datos de un cubo, prisma, cilindro, piramide
     * truncada o cono truncado.
     *
     * @param tipo Tipo de figura.<br>
     * [1] Menu para cubo.<br>
     * [2] Menu para prisma.<br>
     * [3] Menu para cilindro.<br>
     * [4] Menu para cubo desfasado.<br>
     * [5] Menu para prisma desfasado.<br>
     * [6] Menu para cilindro desfasado.<br>
     * [7] Menu para piramide truncada.<br>
     * [8] Menu para cono truncado.<br>
     * @return
     */
    public Displayable cuboPrismaCilindro(int tipo) {
        CuboPrismaCilindro = new Form("Ingrese los datos que se piden:");
        CuboPrismaCilindro.addCommand(atras);
        CuboPrismaCilindro.addCommand(ver);
        CuboPrismaCilindro.setCommandListener(this);
        Radio = new TextField("Radio: ", "0", 5, TextField.NUMERIC);
        NumLados = new TextField("Numero de lados: ", "0", 5, TextField.NUMERIC);
        Altura = new TextField("Altura: ", "0", 5, TextField.NUMERIC);
        if (tipo == 1) {
            // Menu de cubo
            CuboPrismaCilindro.append(Radio);
            tipoSend = 1;
        } else if (tipo == 2) {
            // Menu de Prisma
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(NumLados);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 2;
        } else if (tipo == 3) {
            // Menu de cilindro
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 3;
        } else if (tipo == 4) {
            // Menu cubo desfasado
            CuboPrismaCilindro.append(Radio);
            desfa = 1;
            tipoSend = 7;
        } else if (tipo == 5) {
            // Prisma desfasado
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(NumLados);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 8;
            desfa = 1;
        } else if (tipo == 6) {
            // Menu de cilindro desfasado
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 9;
            desfa = 1;
        } else if (tipo == 7) {
            // Menu de Prisma modificado para presentar piramide truncada
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(NumLados);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 12;
            desfa = 2;
        } else if (tipo == 8) {
            // Menu de cilindro modificado para presentar cilindro truncado
            CuboPrismaCilindro.append(Radio);
            CuboPrismaCilindro.append(Altura);
            tipoSend = 13;
            desfa = 2;
        }
        return CuboPrismaCilindro;
    }

    /**
     * Crea un formulario para los datos de una esfera.
     *
     * @return
     */
    public Displayable esfera() {
        Esfera = new Form("E S F E R A :");
        Esfera.addCommand(atras);
        Esfera.addCommand(ver);
        Esfera.setCommandListener(this);
        NumLados = new TextField("Numero de lados: ", "0", 5, TextField.NUMERIC);
        Radio = new TextField("Radio: ", "0", 5, TextField.NUMERIC);
        Altura = new TextField("Altura: ", "0", 5, TextField.NUMERIC);
        Esfera.append(Radio);
        tipoSend = 6;
        return Esfera;
    }

    public void preparaDatosPintar() {
        /*
         * Este metodo se invoca al pulsar pintar en un formulario. Este metodo
         * recibe la figura a pintar
         */
 /* Todos tienen radio */
        Rad = Float.parseFloat(Radio.getString());
        /*
         * Si es un cilindro, cono o esfera el numero de lados es alto para que
         * parezca circulo
         */
        NumLad = Integer.parseInt(NumLados.getString());
        Altu = Float.parseFloat(Altura.getString());
        p = new Pintar(this);

        if (tipoSend == 1) {
            /*
             * El radio de la figura se calcula a partir del tamaño del lado del
             * cubo
             */
            Altu = Rad;
            Rad = (float) (Rad / Math.sqrt(2));
            NumLad = 4;
            p.tipo = 1;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = 0;
        } else if (tipoSend == 2) {
            /* Para un prisma */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = 0;
        } else if (tipoSend == 3) {
            /* Para un cilindro */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = 30;
            p.Altura = Altu;
            p.des = 0;
        } else if (tipoSend == 4) {
            /* Para una piramide */
            p.tipo = 4;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = 0;
        } else if (tipoSend == 5) {
            /* Para un cono */
            p.tipo = 4;
            p.Radio = Rad;
            p.NumLados = 30;
            p.Altura = Altu;
            p.des = 0;
        } else if (tipoSend == 6) {
            /* Para una esfera */
            p.tipo = 6;
            p.Radio = Rad;
            p.NumLados = 20;
            p.Altura = Rad;
        } else if (tipoSend == 7) {
            Altu = Rad;
            Rad = (float) (Rad / Math.sqrt(2));
            NumLad = 4;
            p.tipo = 1;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 8) {
            /* Para un prisma desfasado */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 9) {
            /* Cilindro desfasado */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = 30;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 10) {
            /* Para una piramide desfasada */
            p.tipo = 4;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 11) {
            /* Para un cono */
            p.tipo = 4;
            p.Radio = Rad;
            p.NumLados = 30;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 12) {
            /* Para un piramide trunca */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = NumLad;
            p.Altura = Altu;
            p.des = desfa;
        } else if (tipoSend == 13) {
            /* Para un cilindro */
            p.tipo = 2;
            p.Radio = Rad;
            p.NumLados = 30;
            p.Altura = Altu;
            p.des = desfa;
        }
        d.setCurrent(p);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command co, Displayable di) {
        if (co == salir) {
            destroyApp(false);
            notifyDestroyed();
        } else if (co == menu.SELECT_COMMAND) {
            switch (menu.getSelectedIndex()) {
                case 0:
                    d.setCurrent(cuboPrismaCilindro(1));
                    break;
                case 1:
                    d.setCurrent(cuboPrismaCilindro(2));
                    break;
                case 2:
                    d.setCurrent(cuboPrismaCilindro(3));
                    break;
                case 3:
                    d.setCurrent(conoPiramide(1));
                    break;
                case 4:
                    d.setCurrent(conoPiramide(2));
                    break;
                case 5:
                    d.setCurrent(esfera());
                    break;
                default:
                    d.setCurrent(menu);
                    break;
            }
        } else if (co == atras) {
            d.setCurrent(menu);
        } else if (co == ver) {
            preparaDatosPintar();
        }
    }

    /**
     * Invoca al metodo respectivo de la figura que se estaba pintando en la
     * pantalla del movil al pulsar la tecla con el comando asociado "atras".
     *
     * @param NumForm Es un identificador para una figura en 3D.
     */
    public void veAtras(int NumForm) {
        switch (NumForm) {
            case 1:
                d.setCurrent(cuboPrismaCilindro(1));
                break;
            case 2:
                d.setCurrent(cuboPrismaCilindro(2));
                break;
            case 3:
                d.setCurrent(cuboPrismaCilindro(3));
                break;
            case 4:
                d.setCurrent(conoPiramide(1));
                break;
            case 5:
                d.setCurrent(conoPiramide(2));
                break;
            case 6:
                d.setCurrent(esfera());
                break;
            default:
                d.setCurrent(menu);
                break;
        }
    }
}

class Pintar extends Canvas implements CommandListener {

    Geometria3D pixi;
    int tipo, NumLados, des;
    float Radio, Altura;
    int centerX, centerY, maxX, maxY, minMaxXY;
    Obj obj = new Obj();
    Command back;

    public Pintar(Geometria3D pix) {
        this.pixi = pix;
        back = new Command("Atrás", Command.BACK, 1);
        this.addCommand(back);
        this.setCommandListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(255, 255, 255);
        /*
         * Cada que se invoca a repaint(), que se pinte el canvas de blanco y
         * poder dibujar la rotacion de la figura.
         */
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        maxX = getWidth();
        maxY = getHeight();
        minMaxXY = Math.min(maxX, maxY);
        centerX = maxX / 2;
        centerY = maxY / 2;
        obj.d = obj.rho * minMaxXY / obj.objSize;

        if (tipo == 1 || tipo == 7) {
            /* Cubos */
            int lados = NumLados;
            lados = lados * 2;
            obj.w = new Point3D[lados];
            obj.vScr = new Point2D[lados];
            obj.w = creaVerticesPrisma(Altura / 2, lados, Radio, des);
            obj.eyeAndScreen();
            int j = 1, ini = 0, fin = lados / 2;

            for (int k = 0; k < lados; k++) {
                for (int i = ini; i < fin; i++) {
                    if (j < fin - 1) {
                        j = i + 1;
                    } else {
                        j = ini;
                    }
                    if (j < lados) {
                        line(g, i, j);
                    }
                }
                ini += lados / 2;
                fin += lados / 2;
            }

            j = lados / 2;
            for (int i = 0; i < lados / 2; i++) {
                j = i + lados / 2;
                line(g, i, j);
            }

        } else if (tipo == 2) {
            /* Prismas */
            int lados = NumLados;
            lados = lados * 2;
            obj.w = new Point3D[lados];
            obj.vScr = new Point2D[lados];
            obj.w = creaVerticesPrisma(Altura / 2, lados, Radio, des);
            obj.eyeAndScreen();
            obj.eyeAndScreen();
            int j = 1, ini = 0, fin = lados / 2;

            for (int k = 0; k < lados; k++) {
                for (int i = ini; i < fin; i++) {
                    if (j < fin - 1) {
                        j = i + 1;
                    } else {
                        j = ini;
                    }
                    if (j < lados) {
                        line(g, i, j);
                    }
                }
                ini += lados / 2;
                fin += lados / 2;
            }

            j = lados / 2;
            for (int i = 0; i < lados / 2; i++) {
                j = i + lados / 2;
                line(g, i, j);
            }
        } else if (tipo == 4) {
            /* Piramides y conos */
            int lados = NumLados;
            lados = lados + 1; // Se cuentan los vertices de la base mas la
            // punta
            obj.w = new Point3D[lados];
            obj.vScr = new Point2D[lados];
            obj.w = creaVerticesCono(Altura / 2, lados, Radio, des);
            obj.eyeAndScreen();
            int j = 1;
            for (int i = 0; i < lados - 1; i++) {
                /* Para unir vertices de la base */
                if (j < lados - 2) {
                    j = i + 1;
                } else {
                    j = 0;
                }
                line(g, i, j);
            }
            for (int i = 0; i < lados - 1; i++) {
                line(g, lados - 1, i);
            }
        } else if (tipo == 6) {
            int lados = NumLados, divisiones = 15;
            obj.w = new Point3D[lados * divisiones];
            obj.vScr = new Point2D[lados * divisiones];
            obj.w = creaVerticesEsfera(Radio, lados, Radio, divisiones);
            obj.eyeAndScreen();
            int j = 1, ini = 0, fin = lados;

            for (int k = 0; k < lados; k++) {
                for (int i = ini; i < fin; i++) {
                    if (j < fin - 1) {
                        j = i + 1;
                    } else {
                        j = ini;
                    }
                    if (j < lados * divisiones) {
                        line(g, i, j);
                    }
                }
                ini += lados;
                fin += lados;
            }
            for (int i = 0; i < lados * divisiones; i++) {
                ini = i;
                fin = ini + 20;
                if (fin < lados * divisiones) {
                    line(g, ini, fin);
                } else {
                    break;
                }
            }
        }

    }

    /**
     *
     * Posee los datos del objeto en 3D.
     *
     */
    class Obj {

        // Posee los datos del objeto 3D
        float rho, theta = 0.5F, phi = 1F, d, objSize;
        float[] v = new float[9];// elementos de la matriz V
        Point3D[] w; // coordenadas universales
        Point2D[] vScr; // coordenadas de la pantalla

        Obj() {
            // objSize es la distancia entre dos vértices opuestos de una figura
            // en 3D.
            objSize = 5F;
            // Para cambiar la perspectiva hacia un objeto en 3D. Acercarse o
            // alejarse.
            rho = 5 * 5;
        }

        void initPersp() {
            float costh = (float) Math.cos(theta);
            float sinth = (float) Math.sin(theta);
            float cosph = (float) Math.cos(phi);
            float sinph = (float) Math.sin(phi);
            v[0] = -sinth;
            v[1] = -cosph * costh;
            v[2] = sinph * costh;
            v[3] = costh;
            v[4] = -cosph * sinth;
            v[5] = sinph * sinth;
            v[6] = sinph;
            v[7] = cosph;
            v[8] = rho;
        }

        void eyeAndScreen() {
            initPersp();
            for (int i = 0; i < w.length; i++) {
                Point3D p = w[i];
                float x = v[0] * p.x + v[3] * p.y;
                float y = v[1] * p.x + v[4] * p.y + v[6] * p.z;
                float z = v[2] * p.x + v[5] * p.y + v[7] * p.z + v[8];
                vScr[i] = new Point2D(-d * x / z, -d * y / z);
            }
        }
    }

    /**
     * Método que calcula los vertices de una figura en 3D. Solo calcula
     * vertices para un cubo, prismas de poligonos regulares de hasta 180 lados
     * (se probo el codigo a mas de 180 lados y no devuelve vertices de una
     * figura regular) y en apariencia cilindros que no es mas que un prisma de
     * poligono regular de mas de 20 lados.
     *
     * @param centerZ Una figura en 3D para este metodo tiene como bases dos
     * poligonos regulares. La ubicacion en el eje Z para cada vertice se hace
     * con respecto al centro de su respectivo poligono regular.
     * @param NumLados Numero de lados que tienen los poligonos base de la
     * figura en 3D. Se divide entre dos para obtener el numero de vertices de
     * un solo poligono.
     * @param Radio Define el radio del poligono de la figura en 3D.
     * @param des Es el desfase de la base superior de la figura en 3D. Modifica
     * Modifica coordenadas X y Y de cada vertice del poligono superior para no
     * quedar alineado con el poligono de abajo. Si el valor de este parametro
     * es igual a cero, no hay desfase.
     * @return Son los vertices de los poligonos de ambas bases de la figura en
     * 3D. Cada vertice representado en ejes cartesianos X, Y, Z.
     */
    public Point3D[] creaVerticesPrisma(double centerZ, int NumLados,
            double Radio, int des) {

        double AnguloInterno = 0, AuxAnguloInterno = 0;
        Point3D[] vertices = new Point3D[NumLados];
        for (int i = 0; i < NumLados; i++) {
            AnguloInterno = Math.toRadians(AuxAnguloInterno);
            if (i < NumLados / 2) {
                vertices[i] = new Point3D(
                        (float) (Radio * Math.cos(AnguloInterno)),
                        (float) (Radio * Math.sin(AnguloInterno)),
                        (float) centerZ);
            } else if (des == 0) {
                vertices[i] = new Point3D(
                        (float) (Radio * Math.cos(AnguloInterno)),
                        (float) (Radio * Math.sin(AnguloInterno)), (float) -1
                        * centerZ);
            } else if (des == 1) {// Con desfase de una unidad.
                vertices[i] = new Point3D(
                        (float) (des + (Radio * Math.cos(AnguloInterno))),
                        (float) (des + (Radio * Math.sin(AnguloInterno))),
                        (float) -1 * centerZ);
            } else if (des == 2) { // Trunco
                vertices[i] = new Point3D(
                        (float) ((Radio * Math.cos(AnguloInterno)) / des),
                        (float) ((Radio * Math.sin(AnguloInterno)) / des),
                        (float) (-1 * centerZ) / des);
            }
            AuxAnguloInterno += 720 / NumLados;
        }
        return vertices;
    }

    /**
     * Metodo que calcula los vertices XYZ de una figura en 3D. Crea los
     * vertices para piramides con base de forma de poligono regular (no mas de
     * 180 lados), para formar un cono basta asignar mas de 10 lados.
     *
     * @param centerZ Una figura en 3D para este metodo tiene como bases un
     * poligono regular. La ubicacion en el eje Z para cada vertice se hace con
     * respecto al centro de su respectivo poligono regular considerando tambien
     * el vertice punta.
     *
     * @param NumLados Numero de lados que tiene el poligono de la base de la
     * figura en 3D.
     * @param Radio Define el radio del poligono de la figura en 3D.
     * @param des Es el desfase del vertice superior de la figura en 3D.
     * Modifica coordenadas X y Y del vertice superior para no quedar alineado
     * con el centro del poligono base. Si el valor de este parametro es igual a
     * cero, no hay desfase.
     * @return Devuelve vertices XYZ de la figura en 3D.
     */
    public Point3D[] creaVerticesCono(double centerZ, int NumLados,
            double Radio, int des) {
        double AnguloInterno = 0, AuxAnguloInterno = 0;
        Point3D[] vertices = new Point3D[NumLados];
        for (int i = 0; i < NumLados - 1; i++) {
            AnguloInterno = Math.toRadians(AuxAnguloInterno);
            vertices[i] = new Point3D(
                    (float) (Radio * Math.cos(AnguloInterno)),
                    (float) (Radio * Math.sin(AnguloInterno)), (float) centerZ);
            AuxAnguloInterno += 360 / (NumLados - 1);
        }
        if (des > 0) {
            vertices[NumLados - 1] = new Point3D(des, des, (float) -1 * centerZ);// Vertice
            // punta
        } else {
            vertices[NumLados - 1] = new Point3D(0, 0, (float) -1 * centerZ);// Vertice
            // punta
        }
        return vertices;
    }

    /**
     * Metodo que calcula los vertices XYZ para una esfera incluyendo su malla.
     *
     * @param centerZ Una figura en 3D para este metodo tiene como bases varios
     * poligonos regulares a lo largo de toda la figura 3D. La ubicacion en el
     * eje Z para cada vertice de los poligonos se hace con respecto al centro
     * de su respectivo poligono regular.
     * @param NumLados Numero de lados que tienen todos los poligonos de la
     * figura en 3D.
     * @param Radio Define el radio de un poligono de la figura en 3D.
     * @param malla Numero de mallas (poligonos regulares) que cubriran a la
     * figura 3D. (aspecto de estar cubierta en una malla o red)
     * @return
     */
    public Point3D[] creaVerticesEsfera(double centerZ, int NumLados,
            double Radio, int malla) {
        double y, x, AnguloInt = 0, AuxAng = 0;
        int k = 0;
        Point3D[] vertices = new Point3D[NumLados * malla];

        double AuxZ = (centerZ * 2) / malla;
        // /
        centerZ = centerZ + (AuxZ / 2);
        // System.out.println(AuxZ);
        for (int i = 0; i < malla; i++) {
            y = Radio * 2 - centerZ; // posicion en eje Z de la malla
            x = Math.sqrt(Math.abs((Radio * Radio) - (y * y))); // Radio de la
            // malla
            /* Creamos una malla */
            centerZ += AuxZ;
            for (int j = 0; j < NumLados; j++) {
                AuxAng = Math.toRadians(AnguloInt);
                vertices[k] = new Point3D((float) x * Math.cos(AuxAng),
                        (float) x * Math.sin(AuxAng), y);
                k++;
                AnguloInt += (360 / NumLados);
            }
            AnguloInt = 0;
        }
        return vertices;
    }

    /**
     * Método que reubica el eje X de un punto tomando como origen del plano
     * cartesiano XYZ (0, 0, 0) el centro de la pantalla.
     *
     * @param x Coordenada de eje X a reubicar.
     * @return Coordenada de eje X reubicada.
     */
    int iX(float x) {
        return (int) (centerX + x);
    }

    /**
     * Método que reubica el eje Y de un punto tomando como origen del plano
     * cartesiano XYZ (0, 0, 0) el centro de la pantalla.
     *
     * @param y Coordenada de eje Y a reubicar.
     * @return Coordenada de eje y reubicada.
     */
    int iY(float y) {
        return (int) (centerY - y);
    }

    /**
     * Metodo que une con una linea visible dos puntos con coordenadas (X, Y)
     * previamente reubicadas en el plano XYZ de la pantalla.
     *
     * @param i Es el primer vértice de una línea.
     * @param j Es el segunto vértice de una línea.
     */
    void line(Graphics g, int i, int j) {
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        g.drawLine(iX(p.x), iY(p.y), iX(q.x), iY(q.y));
    }

    class Point2D {

        float x, y;

        /**
         * Contiene coordenadas XY para un punto.
         *
         * @param x Eje X de un plano cartesiado en 2D para un punto.
         * @param y Eje Y de un plano cartesiado en 2D para un punto.
         */
        Point2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    class Point3D {

        float x, y, z;

        /**
         * Contiene coordenadas XYZ para un punto.
         *
         * @param x Eje X de un plano cartesiado en 3D para un punto.
         * @param y Eje Y de un plano cartesiado en 3D para un punto.
         * @param z Eje Z de un plano cartesiado en 3D para un punto.
         */
        Point3D(double x, double y, double z) {
            this.x = (float) x;
            this.y = (float) y;
            this.z = (float) z;
        }
    }

    /**
     * Método que modifica la perspectiva de una figura en 3D (apariencia de
     * giro) alterando la posicion de todos los puntos de una figura en 3D con
     * un definido valor de giro (0.1) respecto al eje X o el eje Y;
     *
     * @param ex Define factor de rotacion de una figura en 3D respacto al eje
     * X.
     * @param ey Define factor de rotacion de una figura en 3D respacto al eje
     * Y.
     */
    public void mouseDragged(float ex, float ey) {
        obj.theta = obj.theta + ex;
        obj.phi = obj.phi + ey;
    }

    public void keyPressed(int i) {
        float ex = 0, ey = ex;
        switch (getGameAction(i)) {
            case Canvas.DOWN: {
                mouseDragged(ex, ey - 0.1F);
                break;
            }
            case Canvas.UP: {
                mouseDragged(ex, ey + 0.1F);
                break;
            }
            case Canvas.LEFT: {
                mouseDragged(ex + 0.1F, ey);
                break;
            }
            case Canvas.RIGHT: {
                mouseDragged(ex - 0.1F, ey);
                break;
            }
        }
        this.repaint();
    }

    public void commandAction(Command co, Displayable di) {
        if (co == back) {
            pixi.veAtras(tipo);
        }
    }
}
