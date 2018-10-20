package com.ray.mockit;

public interface PersonDao
{  
    public Person fetchPerson( Integer personID );  
    public void update( Person person );  
}   