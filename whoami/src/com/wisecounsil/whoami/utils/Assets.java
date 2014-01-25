package com.wisecounsil.whoami.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener{

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
