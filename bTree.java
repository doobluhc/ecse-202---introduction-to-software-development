
public class bTree {
	bNode root = null;
	double Xcor;
	//inner class
	public class bNode
	{
		bNode left;
		bNode right;
		gBall iBall;

	}


	public void addNode(gBall iBall)
	{

		root = rNode(root,iBall);
	}
	//add iBall(gBall object) into the binary tree
	private bNode rNode(bNode root,gBall iBall)
	{
		if(root==null) {
			bNode node = new bNode();
			node.iBall = iBall;
			node.left=null;
			node.right=null;
			root=node;
			return root;
		}
		else if (iBall.bSize<root.iBall.bSize) {
			root.left= rNode(root.left,iBall);
		}
		else {
			root.right = rNode(root.right,iBall);
		}
		return root;
	}




	public boolean isRunning()
	{
		return checkRun(root);
	}
	//check if each iBall has y velocity by recursion
	//true -> still moving in y direction
	//false-> no y velocity
 	public boolean checkRun(bNode root)
	{
		if(root.left!=null)
		{
			if(checkRun(root.left)==true) return true;
		}
		if(root.iBall.isAlive()==true) return true;
		if(root.right!=null) {
			if(checkRun(root.right)) return true;
		}
		return false;

	}
	public void moveSort()
	{
		inorder(root);
	}
	//traverse the binary tree inorder using recursion
	public void inorder(bNode root)
	{
		if(root.left!=null) inorder(root.left);
		root.iBall.moveTo(Xcor,600-root.iBall.bSize*12);
		Xcor = Xcor + root.iBall.bSize*12;
		if(root.right!=null) inorder(root.right);
	}
}



