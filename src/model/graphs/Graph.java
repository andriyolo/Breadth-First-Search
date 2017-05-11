package model.graphs;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by stefanoandriolo on 19/04/17.
 */
public class Graph<T extends Comparable<T>> implements IGraph<T> {

    HashMap<Node<T>, LinkedList<Node<T>>> vertexes;


    public Graph() {
        this.vertexes = new HashMap<>();
    }

    @Override
    public boolean insertNode(Node<T> u) {

        if (!this.vertexes.containsKey(u)) {
            this.vertexes.put(u, new LinkedList<>());
            return true;
        }

        return false;
    }

    @Override
	//TODO: cancellare u
	public void deleteNode(Node<T> u) {
		
		//cancello gli archi uscenti da u
		if (this.vertexes.containsKey(u)) {
			this.vertexes.get(u).clear() ;
			
			//cancello gli archi incidenti in u
			for (Node<T> n : this.vertexes.keySet()) {
				
				if (this.vertexes.get(n).contains(u))
					this.vertexes.get(n).remove(u) ;
							
			}
			
		}
		
	}

    @Override
    // NOTA: Assumiamo che u e v esistano nel grafo
    public boolean insertEdge(Node<T> u, Node<T> v) {
        if (this.vertexes.containsKey(u) && this.vertexes.containsKey(v)) {
            this.vertexes.get(u).add(v);
            return true;
        }
        
        return false;
    }

    @Override
    public void deleteEdge(Node<T> u, Node<T> v) {
        if (this.vertexes.containsKey(u) && this.vertexes.containsKey(v))
            this.vertexes.get(u).remove(v);
    }

    @Override
    public Set<Node<T>> adj(Node<T> u) {

        HashSet<Node<T>> adjSet = new HashSet<Node<T>>();

        if (this.vertexes.containsKey(u))
            adjSet.addAll(this.vertexes.get(u));

        return adjSet;
    }

    @Override
	public Set<Node<T>> V() {
		
		Set<Node<T>> nodeSet = new TreeSet<Node<T>>() ;
		
		if (!this.vertexes.isEmpty()) {
			nodeSet = this.vertexes.keySet() ;
		}
		
		return nodeSet;
	}

    @Override
    public void print() {
        for (Entry<Node<T>, LinkedList<Node<T>>> e :
                this.vertexes.entrySet()) {
            System.out.print(e.getKey() + " : ");

            for (Node<T> l :
                    e.getValue()) {
                System.out.print(l + " ");
            }

            System.out.println();
        }
    }
    
    
    // ritorna una copia del vettore dei vertici, accessibile solamente alle classi derivate
    // serve per riuscire a costruire il disegno del grafico
    protected HashMap<Node<T>, LinkedList<Node<T>>> getVertexes() {
    	return new HashMap<Node<T>, LinkedList<Node<T>>>(this.vertexes);
    }
    
    
    public Node<T> getMaxKey() {
    	
    	try {
    		// ritorna la chiave con il valore massimo
    		Node<T> maxKey = Collections.max(this.vertexes.keySet());
    		return maxKey;
    	} catch (NoSuchElementException nse) {
    		// se scatta un'eccezione (la collezione è vuota) si ritorna un valore di default
    		return null;
    	}
    }
}
