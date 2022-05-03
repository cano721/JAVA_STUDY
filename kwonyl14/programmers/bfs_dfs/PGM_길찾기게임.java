import java.util.Arrays;

public class Solution {
	
	int trvs[][], idx;
	
	class TreeNode {
		int v, n;  // x, i 
		TreeNode l, r, p;
		TreeNode( int v, int n ) {
			this.v = v;
			this.n = n;
		}
		public void setLeftChild( TreeNode l ) {
			this.l = l;
			if( l != null ) 
				l.p = this;
		}
		public void setRightChild( TreeNode r ) {
			this.r = r;
			if( r != null ) 
				r.p = this;
		}
		public void addNode( TreeNode c, int v, int n ) {  // insert
			TreeNode l = c.l, r = c.r;
			if( v < c.v ) {
				if( l == null )
					c.setLeftChild( new TreeNode( v, n ) );
				else				
					addNode( l, v, n );
			}
			else {
				if( r == null )	
					c.setRightChild( new TreeNode( v, n ) );
				else				
					addNode( r, v, n );
			}
		}
		public void preOrder( TreeNode c ) {  // traversal
			if( c != null ) {
				trvs[0][idx++] = c.n;
				preOrder( c.l );
				preOrder( c.r );
			}
		}
		public void postOrder( TreeNode c ) {  // traversal
			if( c != null ) {
				postOrder( c.l );
				postOrder( c.r );
				trvs[1][idx++] = c.n;
			}
		}
	}
	
	public int[][] solution( int[][] nodeinfo ) {
		int n = nodeinfo.length, i;
		int nd[][] = new int[n][3];
		for( i = 0; i < n; i++ ) {
			nd[i][0] = nodeinfo[i][0];
			nd[i][1] = nodeinfo[i][1];
			nd[i][2] = i+1;
		}
		Arrays.sort( nd, ( n1, n2 ) -> { return n1[1]==n2[1] ? n1[0]-n2[0] : n2[1]-n1[1]; } );
		TreeNode R = new TreeNode( nd[0][0], nd[0][2] );
		for( i = 1; i < n; i++ )
			R.addNode( R, nd[i][0], nd[i][2] );
		trvs = new int[2][n];  // pre/post_order_traversal
		idx = 0;  
		R.preOrder( R );
		idx = 0; 
		R.postOrder( R );
		return trvs;
	}
}