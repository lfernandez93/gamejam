package com.wisecounsil.whoami.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Assets implements Disposable, AssetErrorListener{
	public AssetMenuBackground menuBackground;
	public static final String TAG=Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	private Assets(){}
	
	public void init(AssetManager assetManager){
		this.assetManager = assetManager;
		assetManager.setErrorListener(this);
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a: assetManager.getAssetNames()){
			Gdx.app.debug(TAG, "asset: "+a);
		}
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
		for(Texture t:atlas.getTextures()){
			t.setFilter(TextureFilter.Linear,TextureFilter.Linear);
		}
		menuBackground = new AssetMenuBackground(atlas);
	}
	
	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		Gdx.app.error(TAG, "Couldn't load asset :" + asset.fileName, (Exception)throwable);
	}

	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		assetManager.dispose();
	}

}

class AssetMenuBackground{
	public final AtlasRegion back;
	
	public AssetMenuBackground(TextureAtlas atlas){
		back = atlas.findRegion("Background1");
	}
}

