#! /usr/bin/ruby

# Add .to_key_jumps() method to String class
# Include ability to use a default or custom key map
class String
  @@key_lookup = nil

  def self.default_key_map
    [['A','B','C','D','E','F'],
     ['G','H','I','J','K','L'],
     ['M','N','O','P','Q','R'],
     ['S','T','U','V','W','X'],
     ['Y','Z','1','2','3','4'],
     ['5','6','7','8','9','0']]
  end

  def self.initialize_key_map(key_map=default_key_map)
    @@key_lookup = {}
    key_map.each_with_index do |row,i|
      row.each_with_index do |char,j|
        @@key_lookup[char] = [i,j]
      end
    end
  end

  def to_key_jumps()
    String.initialize_key_map if @@key_lookup.nil?

    string = self
    output = []
    current = @@key_lookup['A'].dup # Starts on 'A'

    string.upcase.chars.each do |char|
      if char == ' '
        output << 'S'
      else
        goal = @@key_lookup[char]
        if goal # Only map to characters in the KEY_MAP
          until(current == goal) do
            if(current[0] < goal[0])
              output << 'D'
              current[0] += 1
            elsif(current[0] > goal[0])
              output << 'U'
              current[0] -= 1
            elsif(current[1] < goal[1])
              output << 'R'
              current[1] += 1
            else
              output << 'L'
              current[1] -= 1
            end
          end
          output << '#'
        end
      end
    end

    output.join(',')
  end
end

# --------------------------------------------------------------------------------------------------
# Test code

# puts "Default Key Map: #{String.default_key_map}"

# Test default 6x6 alphanumeric key map
if 'IT Crowd'.to_key_jumps != 'D,R,R,#,D,D,L,#,S,U,U,U,R,#,D,D,R,R,R,#,L,L,L,#,D,R,R,#,U,U,U,L,#'
  raise "Failed 6x6 alphanumeric test case."
end

# Test custom 1x36 alphanumeric key map
String.initialize_key_map([['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0']])
if 'IT Crowd'.to_key_jumps != 'R,R,R,R,R,R,R,R,#,R,R,R,R,R,R,R,R,R,R,R,#,S,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,#,R,R,R,R,R,R,R,R,R,R,R,R,R,R,R,#,L,L,L,#,R,R,R,R,R,R,R,R,#,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,L,#'
  raise "Failed 1x36 alphanumeric test case."
end

# Test custom 1x3 alpha key map
String.initialize_key_map([['A','B','C']])
if 'IT Crowd'.to_key_jumps != 'S,R,R,#'
  raise "Failed 1x3 alpha test case."
end

# --------------------------------------------------------------------------------------------------
# Script portion accepts:
#   Strings as command line parameters
#   Strings from one or more text files
#   Strings streamed in over STDIN from pipe or terminal

# Force default key map
String.initialize_key_map

# Convert command line parameters to key jumps
if ARGV.length > 0 && !File.exist?(ARGV[0])
  ARGV.each do |line|
    puts "#{line} -> #{line.to_key_jumps}\n"
  end
else # Read from files or stream/pipe
  ARGF.readlines.each do |line|
    puts "#{line.chomp} -> #{line.to_key_jumps}\n"
  end  
end
