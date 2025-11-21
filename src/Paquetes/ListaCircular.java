package Paquetes;

public class ListaCircular {
    private Nodo cabecera;

    public ListaCircular() {
        this.cabecera = null;
    }

    public boolean esVacia() {
        return cabecera == null;
    }

    public void InsertarInicio(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);

        if (esVacia()) {
            cabecera = nuevo;
            nuevo.setSiguiente(cabecera);
        } else {
            Nodo aux = cabecera;

            do {
                aux = aux.getSiguiente(); // aux avanzara hasta ubicarse antes de la cabecera
            } while (aux.getSiguiente() != cabecera);
            aux.setSiguiente(nuevo);
            nuevo.setSiguiente(cabecera);
            cabecera = nuevo;
        }
    }

    public void InsertarFinal(int valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (esVacia()) {
            cabecera = nuevo;
            nuevo.setAnterior(cabecera);
        } else {
            Nodo aux = cabecera;
            do {
                aux = aux.getSiguiente(); // avanzara hasta ubicarse antes de cabecera
            } while (aux.getSiguiente() != cabecera);
            aux.setSiguiente(nuevo); // Antes de cabecera se insertara en nuevo nodo
            nuevo.setSiguiente(cabecera); // Nuevo nodo apunta al primero
        }
    }

    public void MostrarCompleta() {
        if (cabecera == null)  return;

        Nodo aux = cabecera;
        do {
            System.out.println(aux.getValor());
            aux = aux.getSiguiente();

        } while (aux != cabecera);
    }

    public void MostrarIncompleta() {
        if (cabecera == null) return;

        Nodo aux = cabecera;
        do {
            System.out.println(aux.getValor());
            aux = aux.getSiguiente();

        } while (aux.getSiguiente() != cabecera);
    }

    public boolean Buscar(int Abuscar) {
        if (esVacia()) return false;

        Nodo aux = cabecera;
        do {
            if (aux.getValor() == Abuscar) {
                return true;
            } else {
                aux = aux.getSiguiente();
            }
        } while (aux != cabecera);
        return false;
    }

    public void Modificar(int Abuscar, int cambio) {
        if (esVacia()) return;

        Nodo aux = cabecera;
        do {
            if (aux.getValor() == Abuscar) {
                aux.setValor(cambio);
                return;
            } else {
                aux = aux.getSiguiente();
            }
        } while (aux != cabecera);
        System.out.println("Valor no existe en la lista");
    }

    public void EliminaCabecera(){
        if(esVacia()) return;

        Nodo aux = cabecera;
        do{
            aux = aux.getSiguiente();
        } while (aux.getSiguiente()!=cabecera); // Rebote si no ha llegado al ultimo Nodo

        aux.setSiguiente(cabecera.getSiguiente()); // El ultimo nodo se conecta al segundo
        cabecera = cabecera.getSiguiente(); //cabecera ahora apuntara al segundo
        if(aux == cabecera){ //si solo hay un nodo, se lo elimina de forma automatica
            cabecera = null;
        }
    }

    public void EliminarTodo(){
        cabecera = null;
        System.out.println("Lista eliminada");
    }

    public void EliminaUltimo(){
        if(esVacia()) return;

        Nodo anterior, aux = cabecera;
        do{
            anterior=aux; // anterior se ubica en la posicion anterior a aux
            aux=aux.getSiguiente();
        } while (aux.getSiguiente()!= cabecera); // Rebotara hasta llegar al ultimo
        anterior.setSiguiente(aux.getSiguiente());//el penultimo apunta al primero de la lista
        if(aux==cabecera){
            cabecera = null;
        }
    }

    public void Elimina(int Abuscar){
        boolean encontrado = false;
        if(esVacia()) return;

        Nodo ant = cabecera;
        Nodo aux = cabecera;
        do{
            if(aux.getValor() == Abuscar){
                encontrado = true; // si lo encuentra activa la variable
            }else{
                ant=aux;
                aux=aux.getSiguiente();
            }
        }while(aux!=cabecera && !encontrado);

        if (encontrado){
            if(aux == cabecera && aux.getSiguiente()==cabecera){
                cabecera = null; //verifica si es el unico nodo
            }else { // verifica si es el primero de la lisa
                if(aux == cabecera){
                    ant.setSiguiente(cabecera.getSiguiente());
                    cabecera = cabecera.getSiguiente();
                }else{
                    ant.setSiguiente(aux.getSiguiente());
                }
            }
        }

    }
}
