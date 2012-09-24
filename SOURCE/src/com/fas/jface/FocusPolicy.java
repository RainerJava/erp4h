package com.fas.jface;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class FocusPolicy extends FocusTraversalPolicy{

	private List list;
	
	private Map keys=new HashMap();
	public FocusPolicy(List list){
		this.list=list;
		init();
	}
	
	public FocusPolicy(Component[] list){
		List array=new ArrayList();
		for(int i=0;i<list.length;i++){
			array.add(list[i]);
		}
		this.list=array;
		init();
	}
	public void init(){
		for(int i=0;i<list.size();i++){
			keys.put(list.get(i),Integer.toString(i));
		}
	}
	/**
	 * 引数aComponentの次のフォーカスを設定する。
	 * @param focusCycleRoot コンテナ
	 * @param aComponent　　　以前フォーカスのコンポーネント
	 * @return 次のフォーカスコンポーネント
	 */
	public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
		Component retComp = aComponent;
		while(true){
			int index=findComponet(retComp);
			if(index<=-1){
				retComp = (Component)list.get(0);
			} else if(index==list.size()-1){
				retComp = (Component)list.get(0);
			}else{
				retComp = (Component)list.get(index+1);			
			}
			
			if(retComp.isEnabled() || aComponent == retComp){
				break;
			}
		}
		return retComp;
	}
	/**
	 * 引数aComponentの前のフォーカスを設定する。
	 * @param focusCycleRoot コンテナ
	 * @param aComponent　　　以前フォーカスのコンポーネント
	 * @return 前のフォーカスコンポーネント
	 */
	public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
		Component retComp = aComponent;
		while(true){
			int index=findComponet(retComp);
			if(index<=-1){
				retComp = (Component)list.get(0);
			} else if(index==0){
				retComp = (Component)list.get(list.size()-1);
			} else {
				retComp = (Component)list.get(index-1);			
			}
			
			if(retComp.isEnabled() || aComponent == retComp){
				break;
			}
		}
		return retComp;
	}
	/**
	 * デフォルトのフォーカスを設定する。
	 * @param focusCycleRoot コンテナ
	 * @return フォーカスコンポーネント
	 */
	public Component getDefaultComponent(Container focusCycleRoot) {
		return (Component)list.get(0);
	}
	/**
	 * 初期フォーカスを設定する。
	 * @param focusCycleRoot コンテナ
	 * @return フォーカスコンポーネント
	 */
	public Component getFirstComponent(Container focusCycleRoot) {
		return (Component)list.get(0);
	}
	/**
	 * 最終フォーカスを設定する。
	 * @param focusCycleRoot コンテナ
	 * @return フォーカスコンポーネント
	 */
	public Component getLastComponent(Container focusCycleRoot) {
		return (Component)list.get(list.size()-1);
	}
	/**
	 * フォーカスコンポーネントの検索する。
	 * @param comp　　　以前フォーカスのコンポーネント
	 * @return 場所
	 */
	private int findComponet(final Component comp){
		
		Component search=comp;
		while(true){
			Object index=keys.get(search);
			if(index!=null ){
				return Integer.parseInt((String)index);
			}
			search=search.getParent();
			if(search instanceof JFrame || search==null){
				break;
			}
		}
		return -1;
	}
}
