package teralco.sedeelectronica.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

public class PageWrapper<T> {
	@Value("{max.Page.Item.Display}")
	public final int MAX_PAGE_ITEM_DISPLAY = 5;
	private Page<T> page;
	private List<PageItem> items;
	private int currentNumber;
	private String url;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String pUrl) {
		this.url = pUrl;
	}

	public PageWrapper(Page<T> pPage, String pUrl) {
		this.page = pPage;
		this.url = pUrl;
		this.items = new ArrayList<PageItem>();

		this.currentNumber = this.page.getNumber() + 1; // start from 1 to match page.page

		int start, size;
		if (this.page.getTotalPages() <= this.MAX_PAGE_ITEM_DISPLAY) {
			start = 1;
			size = this.page.getTotalPages();
		} else {
			if (this.currentNumber <= this.MAX_PAGE_ITEM_DISPLAY - this.MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 1;
				size = this.MAX_PAGE_ITEM_DISPLAY;
			} else if (this.currentNumber >= this.page.getTotalPages() - this.MAX_PAGE_ITEM_DISPLAY / 2) {
				start = this.page.getTotalPages() - this.MAX_PAGE_ITEM_DISPLAY + 1;
				size = this.MAX_PAGE_ITEM_DISPLAY;
			} else {
				start = this.currentNumber - this.MAX_PAGE_ITEM_DISPLAY / 2;
				size = this.MAX_PAGE_ITEM_DISPLAY;
			}
		}

		for (int i = 0; i < size; i++) {
			this.items.add(new PageItem(start + i, (start + i) == this.currentNumber));
		}
	}

	public Page<T> getPage() {
		return this.page;
	}

	public void setPage(Page<T> pPage) {
		this.page = pPage;
	}

	public List<PageItem> getItems() {
		return this.items;
	}

	public int getNumber() {
		return this.currentNumber;
	}

	public List<T> getContent() {
		return this.page.getContent();
	}

	public int getSize() {
		return this.page.getSize();
	}

	public int getTotalPages() {
		return this.page.getTotalPages();
	}

	public boolean isFirstPage() {
		return this.page.isFirst();
	}

	public boolean isLastPage() {
		return this.page.isLast();
	}

	public boolean isHasPreviousPage() {
		return this.page.hasPrevious();
	}

	public boolean isHasNextPage() {
		return this.page.hasNext();
	}

	public class PageItem {
		private int number;
		private boolean current;

		public PageItem(int pNumber, boolean pCurrent) {
			this.number = pNumber;
			this.current = pCurrent;
		}

		public int getNumber() {
			return this.number;
		}

		public boolean isCurrent() {
			return this.current;
		}
	}
}
