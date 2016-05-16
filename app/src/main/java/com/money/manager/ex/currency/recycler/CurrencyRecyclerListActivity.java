/*
 * Copyright (C) 2012-2016 The Android Money Manager Ex Project Team
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.money.manager.ex.currency.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.money.manager.ex.R;
import com.money.manager.ex.common.BaseFragmentActivity;
import com.money.manager.ex.currency.CurrencyService;
import com.money.manager.ex.view.recycler.DividerItemDecoration;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class CurrencyRecyclerListActivity
    extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_recycler_list);

        initializeList();
    }

    private void initializeList() {
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        if (list == null) return;

        // Layout manager
        list.setLayoutManager(new LinearLayoutManager(this));

        // Adapter
//        CurrencyRecyclerListAdapter adapter = new CurrencyRecyclerListAdapter();
        SectionedRecyclerViewAdapter adapter = new SectionedRecyclerViewAdapter();
        // load data
        CurrencyService service = new CurrencyService(this);

        adapter.addSection(new CurrencySection(getString(R.string.active_currencies), service.getUsedCurrencies()));
        adapter.addSection(new CurrencySection(getString(R.string.inactive_currencies), service.getUnusedCurrencies()));

        //adapter.usedCurrencies = service.getUsedCurrencies();
        //adapter.unusedCurrencies = service.getUnusedCurrencies();

        list.setAdapter(adapter);

        // Separator
        list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}
