/*
 * Copyright 2011 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.kata;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import org.junit.Assert;

/**
 * A company has a {@link MutableList} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final MutableList<Customer> customers = FastList.newList();

    // suppliers are array based.
    private Supplier[] suppliers = new Supplier[0];

    public Company(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addCustomer(Customer aCustomer)
    {
        this.customers.add(aCustomer);
    }

    public MutableList<Customer> getCustomers()
    {
        return this.customers;
    }

    public MutableList<Order> getOrders()
    {
       // Assert.fail("Refactor this code to use GS Collections as part of Exercise 4");
        MutableList<Order> orders = FastList.newList();
       /* for (Customer customer : this.customers)
        {
            orders.addAll(customer.getOrders());
        }*/
        
        orders = customers.flatCollect(new Function<Customer, Iterable<Order>>() {

			@Override
			public Iterable<Order> valueOf(Customer arg0) {
				// TODO Auto-generated method stub
				return arg0.getOrders();
			}
		});
        return orders;
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.getLast();
    }

    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.

        final Supplier[] currentSuppliers = this.suppliers;
        this.suppliers = new Supplier[currentSuppliers.length + 1];
        System.arraycopy(currentSuppliers, 0, this.suppliers, 0, currentSuppliers.length);
        this.suppliers[this.suppliers.length - 1] = supplier;
    }

    public Supplier[] getSuppliers()
    {
        return this.suppliers;
    }

    public Customer getCustomerNamed(final String name)
    {
        
        return customers.detect(new Predicate<Customer>() {

			@Override
			public boolean accept(Customer arg0) {
				// TODO Auto-generated method stub
				return arg0.getName().equalsIgnoreCase(name);
			}
        	
		});
    }
}
