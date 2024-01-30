package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile{
    
    protected final int tileCoordinte;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles(); 

    private Tile(int tileCoordinte) {
        this.tileCoordinte = tileCoordinte;
    }

    public static Tile createTile(final int tileCoordinte, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinte, piece) : EMPTY_TILES_CACHE.get(tileCoordinte);
    }

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();


    public static final class EmptyTile extends Tile{
        
        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{
        
        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordintent, Piece pieceOnTile){
            super(tileCoordintent);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        } 
    }
}