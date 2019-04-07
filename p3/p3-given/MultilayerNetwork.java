import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a multilayer feed-forward neural network.
 * Assumes 2 layers: (input), hidden, and output.
 * Variable conventions will be the same as used in class, 
 * where k indicates an input index, j a hidden index, and i an output index.
 */
public class MultilayerNetwork {

    private int numInput;
    private int numHidden;
    private int numOutput;
    private double learningRate;
    
    /**
     * The weights from the bias node to the hidden layer.
     * bhWeights[a] is the weight from the bias node to hidden node a.
     */
    private double[] bhWeights;
    
    /**
     * The weights from the bias node to the output layer.
     * boWeights[a] is the weight from the bias node to output node a.
     */
    private double[] boWeights;
    
    /**
     * The weights from the input layer to the hidden layer.
     * ihWeights[a][b] is the weight from input node a to hidden node b.
     */
    private double[][] ihWeights;
    
    /**
     * The weights from the hidden layer to the output layer.
     * hoWeights[a][b] is the weight from hidden node a to output node b
     */
    private double[][] hoWeights;
    
    /**
     * Makes a multi-layer neural network with the given specifications.
     */
    public MultilayerNetwork(int numInput, int numHidden, int numOutput, double learningRate) {
        this.numInput = numInput;
        this.numHidden = numHidden;
        this.numOutput = numOutput;
        this.learningRate = learningRate;
        
        bhWeights = new double[numHidden];
        boWeights = new double[numOutput];
        ihWeights = new double[numInput][numHidden];
        hoWeights = new double[numHidden][numOutput];
        
        RandomWeightGenerator rand = new RandomWeightGenerator(0.1);
        
        for(int hID = 0; hID < numHidden; hID++)
            bhWeights[hID] = rand.generateWeight();
            
        for(int oID = 0; oID < numOutput; oID++)
            boWeights[oID] = rand.generateWeight();
            
        for(int k = 0; k < numInput; k++)
            for(int j = 0; j < numHidden; j++)
                ihWeights[k][j] = rand.generateWeight();
        
        for(int j = 0; j < numHidden; j++)
            for(int i = 0; i < numOutput; i++)
                hoWeights[j][i] = rand.generateWeight();
    }
    
    /**
     * Edit this method as you see fit, and then call it with:
     * MultilayerNetwork.makeHardcoded()
     * to create a network with weights hardcoded as you would like,
     * for testing by hand.
     */
    public static MultilayerNetwork makeHardcoded() {
        MultilayerNetwork net = new MultilayerNetwork(2, 2, 1, 1);
        
        net.bhWeights = new double[] {0.1, 0.1};
        net.boWeights = new double[] {-0.1};
        net.ihWeights = new double[][] { new double[] {0.1, 0.1}, new double[] {0.1, 0.1} };
        net.hoWeights = new double[][] { new double[] {-0.1}, new double[] {-0.1} };
        
        return net;
    }
    
    public double[] computeOutput(double[] inputs) {
        // TO DO
        return new double[] {0.0};
    }
    
    /**
     * @param ex The example to train.
     * 
     * Steps:
     * Call computeOutput to get an array of doubles - the outputs of every output unit.
     * Compute Erri for every output unit i.
     * Compute deltai for every output unit i.
     * Compute deltaj for hidden unit j.
     * Compute the new Wj,i weights - the weights from hidden to output.
     * Compute the new Wk,j weights - the weights from input to hidden.
     */
    public void train1Example(Example ex) {
        // TO DO
    }
    
    public void trainEpochs(Example[] examples, int numEpochs) {
        // TO DO
    }
    
    /**
     * Computes the output of this network on each of the given examples,
     * and determines the average error on this output.
     * 
     * @param outputLevel Set to 0 to print only the average error, 1 to also identify each example by name and print its error,
     * or 2 to list each full example and print its error.
     */
    public void runTestSet(Example[] examples, int outputLevel) {
        double errorSum = 0;
        double[] output;
        for(int exID = 0; exID < examples.length; exID++) {
            output = computeOutput(examples[exID].inputs);
            
            for(int outputID = 0; outputID < numOutput; outputID++)
                errorSum += Math.abs(examples[exID].outputs[outputID] - output[outputID]);
                
            if (outputLevel == 1)
                System.out.println("Example " + examples[exID].name + ": " + Arrays.toString(output));
            else if (outputLevel == 2)
                System.out.println("Example " + examples[exID] + ": " + Arrays.toString(output));
        }
        System.out.println("Average output node error: " + (errorSum / (examples.length * examples[0].outputs.length)));
    }
    
    /**
     * Returns a String representation of this network.
     */
    public String toString() {
        String result = "bias->hidden: " + Arrays.toString(bhWeights) + "\n";
        
        result += "input->hidden: [  ";
        for(int inputID = 0; inputID < numInput; inputID++)
            result += Arrays.toString(ihWeights[inputID]) + "  ";
        result += "]\n";
            
        result += "bias->output: " + Arrays.toString(boWeights) + "\n";
        
        result += "hidden->output: [  ";
        for(int hiddenID = 0; hiddenID < numHidden; hiddenID++)
            result += Arrays.toString(hoWeights[hiddenID]) + "  ";
        result += "]\n";
            
        return result;
    }
    
    public static void testMultilayerNetwork() {
        MultilayerNetwork net = MultilayerNetwork.makeHardcoded();
        Example ex = new Example(new double[] {1, 0}, new double[] {1});
        System.out.println(net + "\n");
        
        net.train1Example(ex);
        System.out.println(net);
    }
    
    /**
     * This provides further testing of the MultilayerNetwork implementation.
     * This example uses a network with two output units.
     */
    public static void testMultilayerNetwork2() {
        // Set up a multilayer network with two outputs, for further testing.
        MultilayerNetwork net = new MultilayerNetwork(2, 2, 2, 1);
        
        net.bhWeights = new double[] {0.9, 0.8};
        net.boWeights = new double[] {0.7, 0.6};
        net.ihWeights = new double[][] { new double[] {0.6, 0.5}, new double[] {0.4, 0.3} };
        net.hoWeights = new double[][] { new double[] {0.2, 0.1}, new double[] {1.1, 1.2} };
        
        // Create the example to be tested.
        Example ex = new Example(new double[] {.8, .4}, new double[] {.3, .7});
        
        // Print the network weights and output before training
        System.out.println("------------------------------\nBefore training\n\n" + net);
        System.out.println("output: " + Arrays.toString(net.computeOutput(ex.inputs)) + "\n");
        
        // Train the network
        net.train1Example(ex);
        
        // Print the network weights and output after training
        System.out.println("------------------------------\nAfter training\n\n" + net);
        System.out.println("output: " + Arrays.toString(net.computeOutput(ex.inputs)) + "\n");
    }
	
    public static void learnXorML(int numEpochs) {
        Example ex1 = new Example(new double[] {0, 0}, new double[] {0});
        Example ex2 = new Example(new double[] {0, 1}, new double[] {1});
        Example ex3 = new Example(new double[] {1, 0}, new double[] {1});
        Example ex4 = new Example(new double[] {1, 1}, new double[] {0});
        
        Example[] examples = new Example[] {ex1, ex2, ex3, ex4};
        
        MultilayerNetwork net = new MultilayerNetwork(2, 2, 1, 0.2);
        net.trainEpochs(examples, numEpochs);
        System.out.println(net);
        net.runTestSet(examples, 2);
    }
    
}

