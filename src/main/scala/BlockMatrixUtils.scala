package com.brkyvz.spark

import org.apache.spark.mllib.linalg.{DenseMatrix, Matrix, SparseMatrix}
import org.apache.spark.mllib.linalg.distributed.BlockMatrix

object BlockMatrixUtils {
  
  def map(matrix: BlockMatrix, f: Double => Double): BlockMatrix = {
    
    val mappedRdd = matrix.blocks.map { case ((blockRowIndex, blockColIndex), mat) =>
      val newMat = mat match {
        case dense: DenseMatrix =>
          new DenseMatrix(dense.numRows, dense.numCols, dense.values.map(f), dense.isTransposed)
        case sparse: SparseMatrix =>
          new SparseMatrix(sparse.numRows, sparse.numCols, sparse.colPtrs, 
            sparse.rowIndices, sparse.values.map(f), sparse.isTransposed)
      }
      ((blockRowIndex, blockColIndex), newMat)
    }
    
    new BlockMatrix(mappedRdd, matrix.rowsPerBlock, matrix.colsPerBlock)
  }
  
} 