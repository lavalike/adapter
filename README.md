# dimeno-adapter
> RecyclerView.Adapter与ViewHolder封装，支持加载更多

[![Platform](https://img.shields.io/badge/Platform-Android-00CC00.svg?style=flat)](https://www.android.com)
[![](https://jitpack.io/v/dimeno-tech/dimeno-adapter.svg)](https://jitpack.io/#dimeno-tech/dimeno-adapter)

### 依赖导入

项目根目录

``` gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

模块目录

``` gradle
dependencies {
	implementation 'com.github.dimeno-tech:dimeno-adapter:0.0.9'
}
```

### Adapter

普通Adapter

``` java
public class Adapter extends RecyclerAdapter<T> {
    public Adapter(List<T> list) {
        super(list);
    }

    @Override
    public RecyclerView.ViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
```

加载更多Adapter

``` java
public class Adapter extends LoadRecyclerAdapter<T> {
    public Adapter(List<T> list, ViewGroup parent) {
        super(list, parent);
    }

    @Override
    public RecyclerView.ViewHolder onAbsCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onLoadMore() {

    }
}
```

在onLoadMore回调方法内执行加载更多请求，分情况调用以下方法：
> 请求成功

``` java
setData(List<T> list);
或
addData(List<T> list);
```

> 请求失败

``` java
setState(LoadMoreState.ERROR);
```

> 没有更多数据

``` java
setState(LoadMoreState.NO_MORE);
```

LoadMoreState元注解定义如下

``` java
@Retention(RetentionPolicy.SOURCE)
public @interface LoadMoreState {
    int READY = 0;
    int LOADING = 1;
    int NO_MORE = 2;
    int ERROR = 3;
}
```

可重写LoadRecyclerAdapter的createLoadMoreFooter方法并创建自定义Footer，更多细节请参照默认实现类：LoadMoreFooterImpl

``` java
@Override
protected LoadMoreFooter createLoadMoreFooter() {
    return new Footer(this);
}
```

自定义Footer实现

``` java
public class Footer extends LoadMoreFooter {
    public Footer(OnLoadMoreCallback callback) {
        super(callback);
    }

    @Override
    public void onFooterViewCreated(View itemView) {

    }

    @Override
    public void onStateChange(int state) {

    }

    @Override
    public int layout() {
        return 0;
    }
}
```



添加Header

``` java
mAdapter.addHeader(new RecyclerItem() {
    @Override
    public int layout() {
        return 0;
    }

    @Override
    public void onViewCreated(View itemView) {

    }
}.onCreateView(mRecycler));
```
添加Footer

``` java
mAdapter.addFooter(new RecyclerItem() {
    @Override
    public int layout() {
        return 0;
    }

    @Override
    public void onViewCreated(View itemView) {

    }
}.onCreateView(mRecycler));
```

设置EmptyView

``` java
mAdapter.setEmpty(new RecyclerItem() {
    @Override
    public int layout() {
        return 0;
    }

    @Override
    public void onViewCreated(View itemView) {

    }
}.onCreateView(mRecycler));
```

如需实现多itemType，请重写getAbsItemViewType方法

``` java
@Override
protected int getAbsItemViewType(int position) {
    return super.getAbsItemViewType(position);
}
```

### ViewHolder
ViewHolder继承RecyclerViewHolder，提供两种构造器以供选择

``` java
RecyclerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId);
RecyclerViewHolder(@NonNull View itemView);
```

在bind方法中进行数据绑定

``` java
public class ViewHolder extends RecyclerViewHolder<T> {
    public ViewHolder(@NonNull ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bind() {
        
    }
}
```

同时兼容Adapter和ViewHolder点击/长按事件

ViewHolder实现OnHolderClickCallback / OnHolderLongClickCallback接口，重写相关方法即可

源码逻辑

``` java
if (mItemClickCallback != null || holder instanceof OnHolderClickCallback) {
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mItemClickCallback != null)
                mItemClickCallback.onItemClick(v, compatPosition(holder.getLayoutPosition()));
            if (holder instanceof OnHolderClickCallback)
                ((OnHolderClickCallback) holder).onItemClick(v, compatPosition(holder.getLayoutPosition()));
        }
    });
}
if (mItemLongClickCallback != null || holder instanceof OnHolderLongClickCallback) {
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mItemLongClickCallback != null)
                mItemLongClickCallback.onItemLongClick(v, compatPosition(holder.getLayoutPosition()));
            if (holder instanceof OnHolderLongClickCallback)
                ((OnHolderLongClickCallback) holder).onItemLongClick(v, compatPosition(holder.getLayoutPosition()));
            return false;
        }
    });
}
```