package javalab11.lab;

import javax.persistence.*;

@Entity
public class Gamedata
{
    private int id;
    private int numberOfPlayers;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "number_of_players", nullable = false)
    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers)
    {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gamedata gamedata = (Gamedata) o;

        if (id != gamedata.id) return false;
        if (numberOfPlayers != gamedata.numberOfPlayers) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + numberOfPlayers;
        return result;
    }
}
