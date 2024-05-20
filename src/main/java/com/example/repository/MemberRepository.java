package com.example.repository;

import com.example.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemberRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
        Member member = new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        member.setAge(rs.getInt("age"));
        member.setDepId(rs.getInt("dep_id"));
        return member;
    };


    public List<Member> findAll() {
        String sql = "SELECT id,name,age,dep_id FROM test_members ORDER BY age desc";

        List<Member> memberList = template.query(sql, MEMBER_ROW_MAPPER); // ←ここに実行の処理を書く

        System.out.println("findAll()呼ばれた");

        return memberList;
    }


    public Member load(Integer id) {
        String sql = "SELECT id,name,age,dep_id FROM test_members WHERE id=:id";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        Member member = template.queryForObject(sql, param, MEMBER_ROW_MAPPER);

        System.out.println("load()呼ばれた");

        return member;
    }


    public Member save(Member member) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);

        if (member.getId() == null) { // ←正しい条件に修正する
            String insertSql = "INSERT INTO test_members(name, age, dep_id) "
                    + " VALUES(:name,:age,:depId)";

            // ここに実行処理を書く
            template.update(insertSql, param);
        } else {
            String updateSql = "UPDATE test_members SET name=:name, age=:age, dep_id=:depId WHERE id=:id;";

            // ここに実行処理を書く
            template.update(updateSql, param);
        }

        System.out.println("save()呼ばれた");
        return member;
    }

    public List<Member> findLike(String name) {
        String sql = "SELECT id,name,age,dep_id FROM members WHERE name LIKE :name";

        SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");

        List<Member> memberList = template.query(sql, param,MEMBER_ROW_MAPPER);

        System.out.println("findLike()呼ばれた");

        return memberList;
    }

}
