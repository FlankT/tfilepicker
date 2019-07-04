# tfilepicker

文件选择，可自定义相关界面UI

1. 支持文件选择
2. 支持图片选择
3. 支持文件类型筛选
4. 支持空文件过滤
5. 支持自定义选择view,颜色等
6. 支持批量选择
7. 默认已添加android 6.0权限申请
8. 图片显示Glide版本3.6


示例：
          
	FilePicker.from(this)                        
		.chooseForBrowser()
                .setMaxCount(10)
                .setFileTypes("doc", "docx", "pdf", "ppt", "pptx", "xlsx", "xls")
                .requestCode(Constants.FILES_REQUEST_CODE)
                .start();
	
	
	
       protected void onActivityResult(int requestCode, int resultCode, Intent data) {	
       
        ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                       
           for (EssFile file : essFileList) {
	   
                  Log.e("essFileList",essFileList.toString());
		  
               }
        }
	

使用方式：

Add it in your root build.gradle at the end of repositories:

    allprojects {

	  repositories {
	  
			...
			
			maven { url 'https://jitpack.io' }			
		  }	
	}
	
Add the dependency：

     dependencies {

	        implementation 'com.github.FlankT:tfilepicker:v1.0'
		
	}
	
Thanks:
https://github.com/imLibo/FilePicker
